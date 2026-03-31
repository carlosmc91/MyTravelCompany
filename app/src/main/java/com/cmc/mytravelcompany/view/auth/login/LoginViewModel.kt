package com.cmc.mytravelcompany.view.auth.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.mytravelcompany.domain.usecase.Login
import com.cmc.mytravelcompany.domain.usecase.SaveSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val login: Login, val saveSession: SaveSession) :
    ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    var loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun onEmailChange(email: String) {
        _loginUiState.update { state ->
            state.copy(email = email)
        }
        verifyLogin()
    }

    fun onPasswordChange(password: String) {
        _loginUiState.update { state ->
            state.copy(password = password)
        }
        verifyLogin()
    }

    fun onClickInitSession() {
        viewModelScope.launch {
            _loginUiState.update { it.copy(isLoading = true) }

            val userEntity = withContext(Dispatchers.IO) {
                login.invoke(_loginUiState.value.email, _loginUiState.value.password)
            }

            userEntity?.let { user ->
                saveSession(user)
            }

            _loginUiState.update { state ->
                state.copy(
                    goToMain = userEntity != null,
                )
            }
        }
    }

    private fun verifyLogin() {
        _loginUiState.update { state ->
            state.copy(isLoginEnabled = isEmailValid(state.email) && isPasswordValid(state.password))
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length > 6
    }
}

data class LoginUiState(
    val email: String = "carlos@gmail.com",
    val password: String = "4324523",
    val isLoading: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val goToMain: Boolean = false,
)
