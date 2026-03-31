package com.cmc.mytravelcompany.view.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {
    private val _registerUiState = MutableStateFlow(RegisterUiState())
    var registerUiState: StateFlow<RegisterUiState> = _registerUiState

    fun onTextFieldChange(text: String) {
        _registerUiState.update { state ->
            when (state.currentStep) {
                RegisterStep.PHONE -> state.copy(phone = text, isButtonEnabled = isPhoneValid(text))
                RegisterStep.EMAIL -> state.copy(email = text, isButtonEnabled = isEmailValid(text))
            }
        }
    }

    private fun isPhoneValid(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
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
            val newIsButtonEnabled = when (newStep) {
                RegisterStep.PHONE -> isPhoneValid(state.phone)
                RegisterStep.EMAIL -> isEmailValid(state.email)
            }
            state.copy(currentStep = newStep, isButtonEnabled = newIsButtonEnabled)
        }
    }
}

data class RegisterUiState(
    val phone: String = "",
    val email: String = "",
    val isButtonEnabled: Boolean = false,
    val currentStep: RegisterStep = RegisterStep.PHONE
)

enum class RegisterStep {
    PHONE, EMAIL
}
