package com.eng.learnlang.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route : String,
    val icon : ImageVector,
    val contentDescription :String? =null,
    val enabled : Boolean = true
)