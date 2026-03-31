package com.cmc.mytravelcompany

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cmc.mytravelcompany.view.core.navigation.NavigationWrapper
import com.example.compose.InstaDevTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstaDevTheme {
                NavigationWrapper()
            }
        }
    }
}