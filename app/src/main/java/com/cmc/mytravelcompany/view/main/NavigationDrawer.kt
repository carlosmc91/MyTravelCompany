package com.cmc.mytravelcompany.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.mytravelcompany.R
import kotlinx.coroutines.launch

@Composable
fun MyNavigationDrawer(
    drawerState: DrawerState,
    uiState: MainUiState,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableIntStateOf(57) }
    val myShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                drawerShape = myShape,
                windowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.padding(end = 80.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fondo_mili),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.BottomCenter,
                        modifier = Modifier.matchParentSize()
                    )
                    // Contenido (Textos)
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
                
                Text(
                    stringResource(R.string.navigation_drawer_travel_with_us),
                    modifier = Modifier.padding(horizontal = 28.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                // Luna de miel
                NavigationDrawerItem(
                    label = { 
                        Text(
                            text = stringResource(R.string.navigation_drawer_moon),
                            fontWeight = if (selectedItem == 0) FontWeight.Bold else FontWeight.Normal,
                            fontSize = if (selectedItem == 0) 20.sp else 16.sp
                        ) 
                    },
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color.Transparent,
                        unselectedContainerColor = Color.Transparent
                    )
                )

                // Maldivas
                NavigationDrawerItem(
                    label = { 
                        Text(
                            text = stringResource(R.string.navigation_drawer_maldivas),
                            fontWeight = if (selectedItem == 1) FontWeight.Bold else FontWeight.Normal,
                            fontSize = if (selectedItem == 1) 20.sp else 16.sp
                        ) 
                    },
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color.Transparent,
                        unselectedContainerColor = Color.Transparent
                    )
                )

                // Japón
                NavigationDrawerItem(
                    label = { 
                        Text(
                            text = stringResource(R.string.navigation_drawer_japan),
                            fontWeight = if (selectedItem == 2) FontWeight.Bold else FontWeight.Normal,
                            fontSize = if (selectedItem == 2) 20.sp else 16.sp
                        ) 
                    },
                    selected = selectedItem == 2,
                    onClick = {
                        selectedItem = 2
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color.Transparent,
                        unselectedContainerColor = Color.Transparent
                    )
                )

                // Otros destinos
                NavigationDrawerItem(
                    label = { 
                        Text(
                            text = stringResource(R.string.navigation_drawer_others),
                            fontWeight = if (selectedItem == 3) FontWeight.Bold else FontWeight.Normal,
                            fontSize = if (selectedItem == 3) 20.sp else 16.sp
                        ) 
                    },
                    selected = selectedItem == 3,
                    onClick = {
                        selectedItem = 3
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color.Transparent,
                        unselectedContainerColor = Color.Transparent
                    )
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp, horizontal = 28.dp))

                Text(
                    stringResource(R.string.navigation_drawer_options),
                    modifier = Modifier.padding(horizontal = 28.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                // A medida
                NavigationDrawerItem(
                    label = { 
                        Text(
                            text = stringResource(R.string.navigation_drawer_solution),
                            fontWeight = if (selectedItem == 4) FontWeight.Bold else FontWeight.Normal,
                            fontSize = if (selectedItem == 4) 20.sp else 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        ) 
                    },
                    selected = selectedItem == 4,
                    onClick = {
                        selectedItem = 4
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color.Transparent,
                        unselectedContainerColor = Color.Transparent
                    )
                )
                
                // ¿Quiénes somos?
                NavigationDrawerItem(
                    label = { 
                        Text(
                            text = stringResource(R.string.navigation_drawer_ask),
                            fontWeight = if (selectedItem == 5) FontWeight.Bold else FontWeight.Normal,
                            fontSize = if (selectedItem == 5) 20.sp else 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        ) 
                    },
                    selected = selectedItem == 5,
                    onClick = {
                        selectedItem = 5
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color.Transparent,
                        unselectedContainerColor = Color.Transparent
                    )
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(R.string.navigation_drawer_frecuentes),
                            fontWeight = if (selectedItem == 6) FontWeight.Bold else FontWeight
                                .Normal,
                            fontSize = if (selectedItem == 6) 20.sp else 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    selected = selectedItem == 5,
                    onClick = {
                        selectedItem = 5
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color.Transparent,
                        unselectedContainerColor = Color.Transparent
                    )
                )
            }
        },
        drawerState = drawerState,
        scrimColor = DrawerDefaults.scrimColor,
        content = content
    )
}
