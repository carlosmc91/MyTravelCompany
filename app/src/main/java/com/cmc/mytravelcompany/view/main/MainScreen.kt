package com.cmc.mytravelcompany.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.mytravelcompany.R
import kotlinx.coroutines.launch


@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()) {
    val uiState by mainViewModel.mainUiState.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val deepGoldStart = MaterialTheme.colorScheme.primary
    val deepGoldEnd = MaterialTheme.colorScheme.background

    MyNavigationDrawer(drawerState, uiState) {
        Scaffold(topBar = {
            MyTopAppBar {
                scope.launch {
                    drawerState.open()
                }
            }
        }) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(deepGoldStart, deepGoldEnd)
                        )
                    )
                    .padding(padding)
                    .padding(16.dp), contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .padding(vertical = 20.dp, horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                }

            }
        }

    }
}
