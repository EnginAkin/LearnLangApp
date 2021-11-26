package com.eng.learnlang.presentation.components


import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Class
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.domain.model.BottomNavItem
import com.eng.learnlang.presentation.ui.theme.bottomGray
import com.eng.learnlang.presentation.util.Screen

@Composable
fun ScaffolForComp(
    navController: NavController,
    modifier: Modifier=Modifier,
    showBottomBar : Boolean = true,

    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.TopicWordScreen.route,
            icon = Icons.Outlined.Home,
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.LibraryMainScreen.route,
            icon = Icons.Outlined.LibraryBooks,
            contentDescription = "Library"
        ),
        BottomNavItem(
            route = "",
            icon = Icons.Outlined.Class,
            contentDescription = "Class and Lesson",
            enabled = false

        ),
        BottomNavItem(
            route = Screen.MyWordMainFeedScreen.route,
            icon = Icons.Outlined.ListAlt,
            contentDescription = "List my Word"
        ),
        BottomNavItem(
            route = Screen.ProfileScreen.route,
            icon = Icons.Filled.Person,
            contentDescription = "About"
        ),
    ),
    content:@Composable  ()-> Unit,

    ) {

    Scaffold(
        bottomBar = {
            if(showBottomBar){
                BottomAppBar(
                    backgroundColor = bottomGray,
                    elevation = 0.dp
                ) {
                    BottomNavigation(modifier= Modifier.fillMaxWidth()) {
                        bottomNavItems.forEachIndexed{ i, bottomNavItem ->

                            StandartBottomNavItem(
                                icon = bottomNavItem.icon,
                                contentDescription = bottomNavItem.contentDescription,
                                selected = bottomNavItem.route==navController.currentDestination?.route,
                                enabled = bottomNavItem.enabled
                            ){
                                //viewModel.setSelectedBottomNavITem(i)
                                navController.navigate(bottomNavItem.route)
                            }
                        }
                    }
                }
            }

        },
        modifier = modifier
    ) {
        content()
    }
}