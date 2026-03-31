package com.cmc.mytravelcompany.view.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.mytravelcompany.R
import com.cmc.mytravelcompany.view.core.components.CmcButtonBorder
import com.cmc.mytravelcompany.view.core.components.CmcButtonPrimary

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel = hiltViewModel()) {
    val uiState by registerViewModel.registerUiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("") },
            navigationIcon = {
                IconButton(onClick = { /* Acción de volver */ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            RegisterContent(
                uiState = uiState,
                onTextFieldChange = {
                    registerViewModel.onTextFieldChange(it)
                },
                onChangeStep = {
                    registerViewModel.onStepChanged()
                })
        }
    }
}

@Composable
fun RegisterContent(
    uiState: RegisterUiState,
    onTextFieldChange: (String) -> Unit,
    onChangeStep: () -> Unit
) {
    val title: String
    val subTitle: String
    val labelTextField: String
    val buttonStepText: String
    val securityText: String
    val keyboardType: KeyboardType
    val textFieldValue: String

    when (uiState.currentStep) {
        RegisterStep.PHONE -> {
            title = stringResource(R.string.register_screen_phone_new_account)
            subTitle = stringResource(R.string.register_screen_phone_info)
            labelTextField = stringResource(R.string.register_screen_phone_number)
            buttonStepText = stringResource(R.string.register_screen_with_email)
            securityText = stringResource(R.string.register_screen_phone_security)
            keyboardType = KeyboardType.Phone
            textFieldValue = uiState.phone
        }
        RegisterStep.EMAIL -> {
            title = stringResource(R.string.register_screen_email_new_account)
            subTitle = stringResource(R.string.register_screen_email_info)
            labelTextField = stringResource(R.string.register_screen_email_number)
            buttonStepText = stringResource(R.string.register_screen_with_phone)
            securityText = stringResource(R.string.register_screen_email_security)
            keyboardType = KeyboardType.Email
            textFieldValue = uiState.email
        }
    }

    Text(
        text = title,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    )
    Spacer(modifier = Modifier.height(8.dp))

    Text(subTitle)

    Spacer(modifier = Modifier.height(18.dp))

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textFieldValue,
        onValueChange = { onTextFieldChange(it) },
        label = { Text(labelTextField) },
        shape = RoundedCornerShape(15.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = securityText,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Spacer(Modifier.size(12.dp))

    CmcButtonPrimary(
        text = stringResource(R.string.register_screen_next),
        enabled = uiState.isButtonEnabled
    ) {
        // Acción de siguiente
    }

    Spacer(Modifier.size(16.dp))

    CmcButtonBorder(buttonStepText) {
        onChangeStep()
    }
}
