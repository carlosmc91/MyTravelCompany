package com.cmc.mytravelcompany.view.benefits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.mytravelcompany.domain.entity.BenefitEntity
import com.cmc.mytravelcompany.domain.usecase.GetBenefitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BenefitsViewModel @Inject constructor(
    private val getBenefitsUseCase: GetBenefitsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BenefitsUiState())
    val uiState: StateFlow<BenefitsUiState> = _uiState.asStateFlow()

    init {
        loadBenefits()
    }

    private fun loadBenefits() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val benefits = getBenefitsUseCase()
            _uiState.update {
                it.copy(
                    benefits = benefits,
                    isLoading = false
                )
            }
        }
    }

    fun onBenefitClicked(benefitId: Int) {
        _uiState.update { currentState ->
            val newExpandedId = if (currentState.expandedBenefitId == benefitId) {
                null // Contraer si ya estaba expandido
            } else {
                benefitId // Expandir el nuevo
            }
            currentState.copy(expandedBenefitId = newExpandedId)
        }
    }
}

data class BenefitsUiState(
    val benefits: List<BenefitEntity> = emptyList(),
    val isLoading: Boolean = false,
    val expandedBenefitId: Int? = null
)
