package com.cmc.mytravelcompany.view.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CmcButtonBorder(text: String, onClick: () -> Unit,) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(), border = BorderStroke(1.dp, color =
            MaterialTheme.colorScheme.onBackground),
        onClick = {
            onClick()
        }
    ) {
        Text(text, color = MaterialTheme.colorScheme.onBackground)
    }
}