package com.cmc.mytravelcompany.view.core.navigation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cmc.mytravelcompany.R
import com.cmc.mytravelcompany.view.auth.login.LoginScreen
import com.cmc.mytravelcompany.view.auth.register.RegisterScreen
import com.cmc.mytravelcompany.view.main.MainScreen

@Composable
fun NavigationWrapper(sessionViewModel: SessionViewModel = hiltViewModel()) {
    val state by sessionViewModel.sessionState.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    when (state) {
        is SessionState.Loading -> {
            SplashScreen()
        }

        else -> {
            val startDestination = if (state is SessionState.Logged) Main else Login
            
            NavHost(navController = navController, startDestination = startDestination) {
                composable<Login> {
                    LoginScreen(
                        onNavigateToRegister = { navController.navigate(Register) },
                        onNavigateToMain = {
                            Log.i("Paso por aqerwruii", "Paso por aqui¡")
                            navController.navigate(Main) {
                                popUpTo(Login) { inclusive = true }
                            }
                        }
                    )
                }

                composable<Register> {
                    RegisterScreen()
                }

                composable<Main> {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.ic_alien),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    }
}
