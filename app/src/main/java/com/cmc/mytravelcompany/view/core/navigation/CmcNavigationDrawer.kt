package com.cmc.mytravelcompany.view.core.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.cmc.mytravelcompany.R
import com.cmc.mytravelcompany.view.main.MainUiState
import kotlinx.coroutines.launch

@Composable
fun CmcNavigationDrawer(
    drawerState: DrawerState,
    uiState: MainUiState,
    currentDestination: NavDestination?,
    onNavigate: (Any) -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val myShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                drawerShape = myShape,
                windowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.padding(end = 80.dp)
            ) {
                // Header del Drawer con datos del usuario
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.fondo_mili),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.BottomCenter,
                        modifier = Modifier.matchParentSize()
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(horizontal = 24.dp, vertical = 32.dp)
                    ) {
                        Text(
                            text = uiState.user?.name ?: "Usuario",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "@${uiState.user?.nickname ?: "usuario"}",
                            color = Color.White.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        stringResource(R.string.navigation_drawer_travel_with_us),
                        modifier = Modifier.padding(horizontal = 28.dp, vertical = 16.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // Sección 1: Destinos DINÁMICOS desde Firestore
                    uiState.destinations.forEach { destination ->
                        DrawerItem(
                            label = destination.name,
                            isSelected = currentDestination?.hasRoute<DestinationDetail>() == true &&
                                    navBackStackEntryContainsId(currentDestination, destination.id),
                            onClick = {
                                onNavigate(DestinationDetail(id = destination.id))
                                scope.launch { drawerState.close() }
                            }
                        )
                    }

                    DrawerItem(
                        label = stringResource(R.string.navigation_drawer_others),
                        isSelected = currentDestination?.hasRoute<Others>() == true,
                        onClick = {
                            onNavigate(Others)
                            scope.launch { drawerState.close() }
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp, horizontal = 28.dp))

                    Text(
                        stringResource(R.string.navigation_drawer_options),
                        modifier = Modifier.padding(horizontal = 28.dp, vertical = 16.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    DrawerItem(
                        label = stringResource(R.string.navigation_drawer_solution),
                        isSelected = currentDestination?.hasRoute<Solution>() == true,
                        onClick = {
                            onNavigate(Solution)
                            scope.launch { drawerState.close() }
                        }
                    )

                    DrawerItem(
                        label = stringResource(R.string.navigation_drawer_ask),
                        isSelected = currentDestination?.hasRoute<Ask>() == true,
                        onClick = {
                            onNavigate(Ask)
                            scope.launch { drawerState.close() }
                        }
                    )

                    DrawerItem(
                        label = stringResource(R.string.navigation_drawer_frecuentes),
                        isSelected = currentDestination?.hasRoute<Frecuentes>() == true,
                        onClick = {
                            onNavigate(Frecuentes)
                            scope.launch { drawerState.close() }
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp, horizontal = 28.dp))

                    DrawerItem(
                        label = "Beneficios",
                        isSelected = currentDestination?.hasRoute<Benefits>() == true,
                        onClick = {
                            onNavigate(Benefits)
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        },
        drawerState = drawerState,
        content = content
    )
}

// Función auxiliar para saber si el destino actual coincide con el ID seleccionado
private fun navBackStackEntryContainsId(destination: NavDestination?, id: String): Boolean {
    // Nota: Esto es simplificado, en una implementación real
    // podrías mirar los argumentos del NavBackStackEntry si fuera necesario.
    return false
}

@Composable
private fun DrawerItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        label = { 
            Text(
                text = label,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                fontSize = if (isSelected) 20.sp else 16.sp
            ) 
        },
        selected = isSelected,
        onClick = onClick,
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = Color.Transparent,
            unselectedContainerColor = Color.Transparent,
            selectedTextColor = MaterialTheme.colorScheme.primary
        )
    )
}
