package com.eng.learnlang.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eng.learnlang.presentation.login.LoginScreen
import com.eng.learnlang.presentation.register.RegisterScreen
import com.eng.learnlang.presentation.splash.SplashScreen
import com.eng.learnlang.presentation.topic_word.TopicWords


@Composable
fun Navigation(

) {
    val navController= rememberNavController();
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



    }

}