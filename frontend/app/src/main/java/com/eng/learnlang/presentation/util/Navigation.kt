package com.eng.learnlang.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eng.learnlang.presentation.library_main_feed.LibraryMainFeedScreen
import com.eng.learnlang.presentation.login.LoginScreen
import com.eng.learnlang.presentation.profile.ProfileScreen
import com.eng.learnlang.presentation.register.RegisterScreen
import com.eng.learnlang.presentation.splash.SplashScreen
import com.eng.learnlang.presentation.topic_word.TopicWords
import com.eng.learnlang.presentation.wordlist_main_feed.MyWordListFeedScreen


@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination =Screen.LoginScreen.route ){

        composable(Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(Screen.RegisterScreen.route){
            RegisterScreen(navController = navController)
        }
        composable(Screen.TopicWordScreen.route){
            TopicWords(navController = navController)
        }
        composable(Screen.ProfileScreen.route){
            ProfileScreen(navController)
        }
        composable(Screen.WordMainFeedScreen.route){
            MyWordListFeedScreen(navController)
        }
        composable(Screen.LibraryMainScreen.route){
            LibraryMainFeedScreen(navController = navController)
        }



    }

}