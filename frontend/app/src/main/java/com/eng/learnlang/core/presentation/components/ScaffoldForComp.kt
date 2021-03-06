package com.eng.learnlang.core.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.core.domain.model.BottomNavItem
import com.eng.learnlang.core.presentation.ui.theme.bottomGray
import com.eng.learnlang.core.util.Screen

@Composable
fun ScaffolForComp(
    navController: NavController,
    modifier: Modifier=Modifier,
    state: ScaffoldState,
    showBottomBar : Boolean = true,

    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.TopicWordScreen.route,
            icon = Icons.Outlined.Home,
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.LibraryMainScreen.route,
            icon = Icons.Outlined.LocalLibrary,
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
        modifier = modifier,
        scaffoldState = state
    ) {
        content()
    }
}