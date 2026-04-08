package com.cmc.mytravelcompany.view.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.mytravelcompany.domain.usecase.Login
import com.cmc.mytravelcompany.domain.usecase.SignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val signIn: SignIn) : ViewModel() {
    private val _registerUiState = MutableStateFlow(RegisterUiState())
    var registerUiState: StateFlow<RegisterUiState> = _registerUiState

    fun onTextFieldChange(text: String) {
        _registerUiState.update { state ->
            val isInputValid = when (state.currentStep) {
                RegisterStep.PHONE -> isPhoneValid(text)
                RegisterStep.EMAIL -> isEmailValid(text)
            }
            state.copy(
                phone = if (state.currentStep == RegisterStep.PHONE) text else state.phone,
                email = if (state.currentStep == RegisterStep.EMAIL) text else state.email,
                isPasswordEnabled = isInputValid,
                isButtonEnabled = isInputValid && state.password.length >= 6
            )
        }
    }

    fun osPasswordChange(text: String) {
        _registerUiState.update { state ->
            state.copy(
                password = text,
                isButtonEnabled = state.isPasswordEnabled && text.length >= 6
            )
        }
    }

    private fun isPhoneValid(phone: String): Boolean {
        val spanishMobileRegex = "^(\\+34|0034)?[67]\\d{8}$".toRegex()
        return spanishMobileRegex.matches(phone.replace(" ", ""))
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun onStepChanged() {
        _registerUiState.update { state ->
            val newStep = when (state.currentStep) {
                RegisterStep.PHONE -> RegisterStep.EMAIL
                RegisterStep.EMAIL -> RegisterStep.PHONE
            }
            val isInputValid = when (newStep) {
                RegisterStep.PHONE -> isPhoneValid(state.phone)
                RegisterStep.EMAIL -> isEmailValid(state.email)
            }
            state.copy(
                currentStep = newStep,
                isPasswordEnabled = isInputValid,
                isButtonEnabled = isInputValid && state.password.length >= 6
            )
        }
    }

    fun onClickRegister() {
        viewModelScope.launch {
            _registerUiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                // El UseCase ahora devuelve el UserEntity o lanza error
                val userEntity = signIn(registerUiState.value.email, registerUiState.value.password)
                _registerUiState.update {
                    it.copy(isLoading = false, goToMain = userEntity != null)
                }
            } catch (e: Exception) {
                _registerUiState.update {
                    it.copy(isLoading = false, errorMessage = "Error: ${e.message}")
                }
            }
        }
    }
}

data class RegisterUiState(
    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordEnabled: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val goToMain: Boolean = false,
    val errorMessage: String? = null,
    val currentStep: RegisterStep = RegisterStep.PHONE
)

enum class RegisterStep {
    PHONE, EMAIL
}
