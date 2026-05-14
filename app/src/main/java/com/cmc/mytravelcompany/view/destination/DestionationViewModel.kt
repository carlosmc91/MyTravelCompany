package com.cmc.mytravelcompany.view.destination

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.mytravelcompany.domain.entity.DestinationEntity
import com.cmc.mytravelcompany.domain.usecase.GetDestinationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(
    private val getDestinationsUseCase: GetDestinationsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<DestinationUiState>(DestinationUiState.Loading)
    val uiState: StateFlow<DestinationUiState> = _uiState.asStateFlow()

    init {
        val destinationId: String? = savedStateHandle["id"]
        destinationId?.let { loadDestination(it) } ?: run {
            _uiState.value = DestinationUiState.Error("ID de destino no encontrado")
        }
    }

    private fun loadDestination(id: String) {
        viewModelScope.launch {
            _uiState.value = DestinationUiState.Loading
            try {
                val destination = getDestinationsUseCase(id)
                if (destination != null) {
                    _uiState.value = DestinationUiState.Success(destination = destination)
                } else {
                    _uiState.value = DestinationUiState.Error("Destino no encontrado")
                }
            } catch (e: Exception) {
                _uiState.value = DestinationUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun onBenefitToggle(benefitId: Int) {
        val currentState = _uiState.value
        if (currentState is DestinationUiState.Success) {
            _uiState.value = currentState.copy(
                expandedBenefitId = if (currentState.expandedBenefitId == benefitId) null else benefitId
            )
        }
    }
}

sealed interface DestinationUiState {
    data object Loading : DestinationUiState
    data class Success(
        val destination: DestinationEntity,
        val expandedBenefitId: Int? = null
    ) : DestinationUiState
    data class Error(val message: String) : DestinationUiState
}
