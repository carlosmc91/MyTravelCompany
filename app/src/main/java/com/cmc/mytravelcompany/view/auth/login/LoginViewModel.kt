package com.cmc.mytravelcompany.view.auth.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.mytravelcompany.domain.usecase.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: Login
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    fun onEmailChange(email: String) {
        _loginUiState.update { it.copy(email = email) }
        verifyLogin()
    }

    fun onPasswordChange(password: String) {
        _loginUiState.update { it.copy(password = password) }
        verifyLogin()
    }

    fun onClickInitSession() {
        viewModelScope.launch {
            _loginUiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                // El UseCase ahora devuelve el UserEntity o lanza error
                val userEntity = login(_loginUiState.value.email, _loginUiState.value.password)
                
                _loginUiState.update { 
                    it.copy(isLoading = false, goToMain = userEntity != null) 
                }
            } catch (e: Exception) {
                _loginUiState.update { 
                    it.copy(isLoading = false, errorMessage = "Error: ${e.message}") 
                }
            }
        }
    }

    private fun verifyLogin() {
        _loginUiState.update { state ->
            state.copy(isLoginEnabled = isEmailValid(state.email) && isPasswordValid(state.password))
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val goToMain: Boolean = false,
    val errorMessage: String? = null
)
