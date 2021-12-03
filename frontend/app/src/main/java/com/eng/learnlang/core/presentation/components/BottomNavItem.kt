package com.eng.learnlang.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.StandartBottomNavItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String? = null,
    selected: Boolean = false,
    selectedColor: Color = MaterialTheme.colors.primary,
    unSelectedColor: Color = MaterialTheme.colors.secondaryVariant,
    enabled: Boolean = true,
    onclick: () -> Unit
) {

    BottomNavigationItem(
        selected = selected,
        onClick = onclick,
        modifier = modifier.background(MaterialTheme.colors.onSecondary),
        enabled = enabled,
        selectedContentColor = if (selected) selectedColor else unSelectedColor,
        unselectedContentColor = if (!enabled) Color.Gray else unSelectedColor,
        icon = {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.onSecondary)
                    .fillMaxSize()
                    .padding(bottom = 5.dp)
                    .drawBehind {
                        if (selected) {
                            drawLine(
                                color = if (selected) selectedColor else unSelectedColor,
                                start = Offset(
                                    size.width / 2f - 15.dp.toPx(),
                                    size.height
                                ),
                                end = Offset(
                                    size.width / 2f + 15.dp.toPx(),
                                    size.height
                                ),
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round,
                            )
                        }
                    }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    )
}