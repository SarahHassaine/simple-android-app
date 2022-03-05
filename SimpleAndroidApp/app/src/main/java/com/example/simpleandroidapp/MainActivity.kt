package com.example.simpleandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.simpleandroidapp.ui.theme.SimpleAndroidAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAndroidAppTheme {
                AlbumScreen(emptyList())
            }
        }
    }
}
