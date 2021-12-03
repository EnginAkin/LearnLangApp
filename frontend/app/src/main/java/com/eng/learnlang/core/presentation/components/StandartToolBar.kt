package com.eng.learnlang.core.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun StandartToolBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    backgroundColor: Color =MaterialTheme.colors.onSecondary,
    imageVector: ImageVector=Icons.Default.ArrowBack,
    showBackArrow: Boolean = false,
    navAction: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = title,
        elevation = 0.dp,
        modifier = modifier,
        navigationIcon = {
            if (showBackArrow) {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "Description",
                        tint = Color.White
                    )
                }
            } else null
        },
        actions = navAction,
        backgroundColor = backgroundColor,
    )

}