package com.eng.learnlang.presentation.components


import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.R
import com.eng.learnlang.domain.model.BottomNavItem
import com.eng.learnlang.presentation.ui.theme.bottomGray
import com.eng.learnlang.presentation.ui.theme.hintgray
import com.eng.learnlang.presentation.util.Screen

@Composable
fun ScaffolForComp(
    navController: NavController,
    modifier: Modifier=Modifier,
    showBottomBar : Boolean = true,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.TopicWordScreen.route,
            icon = Icons.Filled.Home,
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.LibraryMainScreen.route,
            icon = Icons.Filled.LibraryBooks,
            contentDescription = "Library"
        ),
        BottomNavItem(
            route = "",
            icon = Icons.Filled.Class,
            contentDescription = "Class and Lesson",
            enabled = false

        ),
        BottomNavItem(
            route = Screen.WordMainFeedScreen.route,
            icon = Icons.Filled.List,
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