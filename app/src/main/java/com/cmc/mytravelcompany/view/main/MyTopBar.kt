package com.cmc.mytravelcompany.view.main

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.cmc.mytravelcompany.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onNavSelected: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(R.drawable.logo), contentDescription = "Logo"
            )
        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ), navigationIcon = {
            IconButton(onClick = onNavSelected) {
                Icon(
                    imageVector = Icons.Sharp.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        })
}
