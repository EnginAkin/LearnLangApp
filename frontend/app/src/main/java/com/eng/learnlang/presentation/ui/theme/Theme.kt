package com.eng.learnlang.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = greenAccent,
    background = darkgray,
    onBackground = textWhite,
    onPrimary = darkgray,
    surface = feed_color,
    onSurface = lightgray,



)

@Composable
fun LearnlangTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {



    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}