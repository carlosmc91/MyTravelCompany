package com.cmc.mytravelcompany.view.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CmcButtonBorderMain(
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
        border = BorderStroke(2.dp, color = color),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color(0x90000000)
        ),
        onClick = { onClick() },
        contentPadding = paddingValues
    ) {
        Text(text, color = color, fontSize = 20.sp)
    }
}
