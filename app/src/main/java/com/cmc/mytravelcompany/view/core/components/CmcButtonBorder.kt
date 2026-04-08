package com.cmc.mytravelcompany.view.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CmcButtonBorder(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        border = BorderStroke(1.dp, color = color),
        onClick = { onClick() },
        contentPadding = paddingValues
    ) {
        Text(text, color = color)
    }
}
