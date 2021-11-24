package com.eng.learnlang.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = lightgray
    ),
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = textWhite
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = textWhite

    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color= textWhite
    )
)