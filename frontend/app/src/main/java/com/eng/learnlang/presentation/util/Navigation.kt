package com.eng.learnlang.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eng.learnlang.presentation.TopicWordDetailsScreen.TopicWordDetailScreen
import com.eng.learnlang.presentation.library_main_feed.LibraryMainFeedScreen
import com.eng.learnlang.presentation.login.LoginScreen
import com.eng.learnlang.presentation.profile.ProfileScreen
import com.eng.learnlang.presentation.register.RegisterScreen
import com.eng.learnlang.presentation.splash.SplashScreen
import com.eng.learnlang.presentation.topic_word.TopicWords
import com.eng.learnlang.presentation.topic_word_details.TopicWordDetailContentScreen
import com.eng.learnlang.presentation.wordlist_main_feed.MyWordListFeedScreen
import com.eng.learnlang.presentation.wordlist_word_details.MyWordListDetailScreen


@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination =Screen.TopicWordScreen.route ){

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
        composable(Screen.MyWordMainFeedScreen.route){
            MyWordListFeedScreen(navController)
        }
        composable(Screen.LibraryMainScreen.route){
            LibraryMainFeedScreen(navController = navController)
        }
        composable(Screen.MyWordListDetailScreen.route){
            MyWordListDetailScreen(navController)
        }

        composable(Screen.TopicWordDetailContentScreen.route){
            TopicWordDetailContentScreen(navController)
        }
        composable(Screen.TopicWordDetailScreen.route){
            TopicWordDetailScreen(navController = navController)
        }



    }

}