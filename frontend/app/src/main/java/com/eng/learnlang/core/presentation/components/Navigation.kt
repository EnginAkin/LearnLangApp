package com.eng.learnlang.presentation.util

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.feature_main.presentation.TopicWordDetailsScreen.TopicWordDetailScreen
import com.eng.learnlang.feature_library.presentation.library_details.LibraryDetailScreen
import com.eng.learnlang.feature_library.presentation.library_main_feed.LibraryMainFeedScreen
import com.eng.learnlang.feature_auth.presentation.login.LoginScreen
import com.eng.learnlang.feature_profile.presentation.profile.ProfileScreen
import com.eng.learnlang.feature_auth.presentation.register.RegisterScreen
import com.eng.learnlang.feature_auth.presentation.splash.SplashScreen
import com.eng.learnlang.presentation.test_detail.TeachDetailScreen
import com.eng.learnlang.presentation.test_detail.TestScreen
import com.eng.learnlang.feature_main.presentation.topic_word.TopicWords
import com.eng.learnlang.presentation.topic_word_details.TopicWordDetailContentScreen
import com.eng.learnlang.feature_mywordlist.presentation.wordlist_main_feed.MyWordListFeedScreen
import com.eng.learnlang.feature_mywordlist.presentation.wordlist_word_details.MyWordListDetailScreen


@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route ){

        composable(Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(navController, scaffoldState = scaffoldState)
        }
        composable(Screen.RegisterScreen.route){
            RegisterScreen(navController = navController,scaffoldState)
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
        composable(Screen.LibraryDetailScreen.route){
            LibraryDetailScreen(scaffoldState)
        }
        composable(Screen.MyWordListDetailScreen.route){
            MyWordListDetailScreen(navController)
        }

        composable(
            Screen.TopicWordDetailContentScreen.route+"" +
                    "?categoryName={categoryName}" +
                    "?day={day}" +
                    "?limit={limit}",
            arguments = listOf(
                navArgument(
                    name="categoryName"
                ){
                    type= NavType.StringType
                },
                navArgument(
                    name="day"
                ){
                    type= NavType.IntType
                },
                navArgument(
                    name="limit"
                ){
                    type= NavType.IntType
                }
            )

        ){
            TopicWordDetailContentScreen(navController, scaffoldState = scaffoldState)
        }
        composable(
            Screen.TopicWordDetailScreen.route+"/{categoryName}",
            arguments = listOf(
                navArgument(
                    name="categoryName"
                ){
                    type= NavType.StringType
                }
            )
        ){
            TopicWordDetailScreen(navController = navController)
        }
        composable(
            Screen.TeachDetailScreen.route+"?wordsId={wordsId}",
            arguments = listOf(
                navArgument(
                    name = "wordsId"
                ){
                    type= NavType.StringType
                }
            )
        ){
            TeachDetailScreen(navController = navController)
        }
        composable(Screen.TestDetailScreen.route,){
            TestScreen(navController = navController)
        }




    }

}