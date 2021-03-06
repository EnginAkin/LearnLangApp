package com.eng.learnlang.core.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.eng.learnlang.core.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import com.eng.learnlang.feature_library.domain.TranslatorBackgroundTask




@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnlangTheme {
                // A surface container using the 'background' color from the theme
                println("Util test : ip 4 adress : ${Utils.getIPAddress(true)}")
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
                        state=scaffoldState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Navigation(navController,scaffoldState)

                    }
                }
            }
        }


    }

}

