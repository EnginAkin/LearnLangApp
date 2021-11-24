package com.eng.learnlang.presentation.util

sealed class Screen(val route : String){
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object WordMainFeedScreen : Screen("word_main_feed_screen")
    object TopicWordDetailsScreen : Screen("topic_word_detail_screen")
    object QuestionDetailScreen : Screen("question_detail_screen")
    object ProfileScreen : Screen("profile_screen")
    object LibraryDetailScreen : Screen("library_detail_screen")
    object TopicWordDetailScreen : Screen("topic_word_detail_screen")
    object TeachDetailScreen : Screen("teach_detail_screen")
    object TopicWordScreen : Screen("topic_word_screen")
}
