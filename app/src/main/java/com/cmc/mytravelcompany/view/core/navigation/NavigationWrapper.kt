package com.cmc.mytravelcompany.view.core.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cmc.mytravelcompany.R
import com.cmc.mytravelcompany.view.auth.login.LoginScreen
import com.cmc.mytravelcompany.view.auth.register.RegisterScreen
import com.cmc.mytravelcompany.view.benefits.BenefitScreen
import com.cmc.mytravelcompany.view.core.components.MyBackgroundRegister
import com.cmc.mytravelcompany.view.destination.DestinationScreen
import com.cmc.mytravelcompany.view.main.MainScreen
import com.cmc.mytravelcompany.view.main.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun NavigationWrapper(
    sessionViewModel: SessionViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val sessionState by sessionViewModel.sessionState.collectAsStateWithLifecycle()
    val mainUiState by mainViewModel.mainUiState.collectAsStateWithLifecycle()

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    when (sessionState) {
        is SessionState.Loading -> SplashScreen()
        else -> {
            val startDestination = if (sessionState is SessionState.Logged) Main else Login

            if (sessionState is SessionState.Logged) {
                CmcNavigationDrawer(
                    drawerState = drawerState,
                    uiState = mainUiState,
                    currentDestination = currentDestination,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(Main) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                ) {
                    NavHost(navController = navController, startDestination = startDestination) {
                        addAppGraphs(
                            navController = navController,
                            onOpenDrawer = { scope.launch { drawerState.open() } }
                        )
                    }
                }
            } else {
                NavHost(navController = navController, startDestination = startDestination) {
                    addAppGraphs(navController = navController, onOpenDrawer = {})
                }
            }
        }
    }
}

fun NavGraphBuilder.addAppGraphs(
    navController: NavController,
    onOpenDrawer: () -> Unit
) {
    composable<Login> {
        LoginScreen(
            onNavigateToRegister = { navController.navigate(Register) },
            onNavigateToMain = {
                navController.navigate(Main) {
                    popUpTo(Login) { inclusive = true }
                }
            }
        )
    }

    composable<Register> {
        RegisterScreen(onPressBackArrow = {
            navController.navigate(Login) {
                popUpTo(Login) { inclusive = true }
            }
        })
    }

    composable<Main> {
        MainScreen(
            onOpenDrawer = onOpenDrawer
        )
    }

    composable<Benefits> {
        BenefitScreen(onPressBackArrow = {
            navController.navigate(Main) {
                popUpTo(Main) { inclusive = true }
            }
        })
    }

    // Nueva ruta dinámica que maneja todos los destinos de Firebase
    composable<DestinationDetail> {backStackEntry ->
        val detail = backStackEntry.toRoute<DestinationDetail>()
        DestinationScreen(onPressBackArrow = {
            navController.navigate(Main) {
                popUpTo(Main) { inclusive = true }
            }
        })
    }

    composable<Others> { NavPlaceholder("Otros Destinos", onOpenDrawer) }
    composable<Solution> { NavPlaceholder("A medida", onOpenDrawer) }
    composable<Ask> { NavPlaceholder("¿Quiénes somos?", onOpenDrawer) }
    composable<Frecuentes> { NavPlaceholder("Frecuentes", onOpenDrawer) }
}

@Composable
fun NavPlaceholder(title: String, onOpenDrawer: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Pantalla de $title", style = MaterialTheme.typography.headlineMedium)
            IconButton(onClick = onOpenDrawer) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        MyBackgroundRegister(PaddingValues(0.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    }
}
