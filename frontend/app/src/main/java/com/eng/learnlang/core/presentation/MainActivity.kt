package com.eng.learnlang.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eng.learnlang.core.presentation.components.ScaffolForComp
import com.eng.learnlang.core.presentation.ui.theme.LearnlangTheme
import com.eng.learnlang.presentation.util.Navigation
import com.eng.learnlang.core.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnlangTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController= rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val scaffoldState = rememberScaffoldState()

                    ScaffolForComp(
                        navController = navController,
                        showBottomBar = navBackStackEntry?.destination?.route in listOf<String>(
                            Screen.ProfileScreen.route,
                            Screen.MyWordMainFeedScreen.route,
                            Screen.LibraryMainScreen.route,
                            Screen.TopicWordScreen.route
                        ),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Navigation(navController,scaffoldState)

                    }
                }
            }
        }
    }
}

