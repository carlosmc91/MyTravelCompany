package com.cmc.mytravelcompany.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.mytravelcompany.domain.entity.UserEntity
import com.cmc.mytravelcompany.domain.usecase.GetSession
import com.cmc.mytravelcompany.domain.usecase.Logout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSession: GetSession,
    private val logOut: Logout
) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState: StateFlow<MainUiState> = _mainUiState

    init {
        viewModelScope.launch {
            getSession().collect { user ->
                _mainUiState.update { it.copy(user = user) }
            }
        }
    }

    fun onLogout() {
        viewModelScope.launch {
            logOut()
        }
    }
}

data class MainUiState(
    val user: UserEntity? = null
)
