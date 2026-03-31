package com.cmc.mytravelcompany.view.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CmcButtonPrimary(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(),
        onClick = {
            onClick()
        }, enabled = enabled) {
        Text(text=text, modifier = Modifier.padding(3.dp))
    }
}