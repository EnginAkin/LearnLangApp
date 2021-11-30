package com.eng.learnlang.presentation.util

sealed class Screen(val route : String){
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object MyWordMainFeedScreen : Screen("word_main_feed_screen")
    object MyWordListDetailScreen : Screen("my_word_list_details_screen")
    object QuestionDetailScreen : Screen("question_detail_screen")
    object ProfileScreen : Screen("profile_screen")
    object LibraryDetailScreen : Screen("library_detail_screen")
    object LibraryMainScreen : Screen("library_main_screen")
    object TopicWordDetailScreen : Screen("topic_word_detail_screen")
    object TopicWordDetailContentScreen : Screen("topic_word_detail_content_screen")
    object TeachDetailScreen : Screen("teach_detail_screen")
    object TestDetailScreen : Screen("test_detail_screen")
    object TopicWordScreen : Screen("topic_word_screen")
    object SearchScreen : Screen("search_Screen")
}
