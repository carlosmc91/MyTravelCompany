package com.cmc.mytravelcompany.view.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyBackgroundRegister(padding: PaddingValues) {
    val goldStart = MaterialTheme.colorScheme.background
    val goldEnd = Color(0xFFC7A15E)

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(goldEnd, goldStart)
                    )
                )
                .padding(padding)
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {
        }
    }
}