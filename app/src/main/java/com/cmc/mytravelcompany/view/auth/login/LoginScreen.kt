package com.cmc.mytravelcompany.view.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.mytravelcompany.R
import com.cmc.mytravelcompany.view.core.components.CmcButtonBorder
import com.cmc.mytravelcompany.view.core.components.CmcButtonPrimary
import com.cmc.mytravelcompany.view.core.components.MyBackgroundRegister

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    val uiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.goToMain) {
        if (uiState.goToMain) {
            onNavigateToMain()
        }
    }

    if (uiState.isLoading) {
        LoadingView()
    } else {
        LoginView(
            uiState = uiState,
            onEmailChange = { loginViewModel.onEmailChange(it) },
            onPasswordChange = { loginViewModel.onPasswordChange(it) },
            onLoginClick = { loginViewModel.onClickInitSession() },
            onNavigateToRegister = onNavigateToRegister
        )
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.Blue)
    }
}

@Composable
fun LoginView(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Scaffold { padding ->
        MyBackgroundRegister(padding)
        MyContent(
            padding, uiState, onEmailChange, onPasswordChange, onLoginClick, onNavigateToRegister
        )
    }

}

@Composable
fun MyContent(
    padding: PaddingValues,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.weight(0.5f))

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            contentScale = androidx.compose.ui.layout.ContentScale.FillWidth,
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo"
        )

        Spacer(Modifier.weight(1f))

        // Campos de entrada
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.email,
            onValueChange = onEmailChange,
            label = { Text(stringResource(R.string.login_screen_email)) },
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.login_screen_password)) },
            shape = RoundedCornerShape(15.dp),
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(12.dp))

        CmcButtonPrimary(
            text = stringResource(R.string.login_screen_init_session),
            enabled = uiState.isLoginEnabled,
            onClick = onLoginClick
        )

        TextButton(onClick = { /* Olvidé mi contraseña */ }) {
            Text(stringResource(R.string.login_screen_forgot_password))
        }

        Spacer(Modifier.weight(1f))

        CmcButtonBorder(stringResource(R.string.login_screen_new_account)) {
            onNavigateToRegister()
        }

        Spacer(Modifier.height(12.dp))

    }
}
