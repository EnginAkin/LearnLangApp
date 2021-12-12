package com.eng.learnlang.feature_main.presentation.TopicWordDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.core.domain.model.TopicWordDay
import com.eng.learnlang.core.presentation.components.StandartToolBar
import com.eng.learnlang.core.presentation.components.TopicWordDayPost
import com.eng.learnlang.core.util.Screen

@Composable
fun TopicWordDetailScreen(
    navController: NavController,
    topicWordDetailViewModel: TopicWordDetailViewModel= hiltViewModel()
) {
/*
    var listOfItems = listOf(
        TopicWordDay(
            categoryName = "A-1",
            0,
            learnedCountWord = 8
        ),
        TopicWordDay(
            categoryName = "A-1",
            1,
            learnedCountWord = 5
        ),
        TopicWordDay(
            categoryName = "A-1",
            2,
            learnedCountWord = 4
        ), TopicWordDay(
            categoryName = "A-1",
            3,
            learnedCountWord = 9
        ), TopicWordDay(
            categoryName = "A-1",
            4,
            learnedCountWord = 1
        ), TopicWordDay(
            categoryName = "A-1",
            5,
            learnedCountWord = 3
        ), TopicWordDay(
            categoryName = "A-1",
            6,
            learnedCountWord = 10
        )
    )

 */
    val state=topicWordDetailViewModel.state.value
   Column(modifier = Modifier.fillMaxWidth()) {
       StandartToolBar(
           navController = navController,
           title = {
               Text(text = "Words", fontWeight = FontWeight.Bold, color = Color.White)
           },
           modifier = Modifier.fillMaxWidth(),
           showBackArrow = true,

           )
       LazyColumn(modifier = Modifier.fillMaxWidth()) {
           state.topicList?.let {
               itemsIndexed(it.toList()){ i: Int, topicWordDay: TopicWordDay ->
                   TopicWordDayPost(
                      topicWordDay=topicWordDay,
                       onclickListener = {topicWordDay -> 
                           navController.navigate(Screen.TopicWordDetailContentScreen.route)
                       }
                   )

               }
           }
       }

   }

}