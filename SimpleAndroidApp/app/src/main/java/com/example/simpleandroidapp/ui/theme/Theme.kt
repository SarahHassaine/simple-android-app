package com.example.simpleandroidapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = LightGreen500,
    primaryVariant = LightGreen200,
    secondary = Orange
)

private val LightColorPalette = lightColors(
    primary = LightGreen700,
    primaryVariant = LightGreen500,
    secondary = Orange
)

@Composable
fun SimpleAndroidAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}