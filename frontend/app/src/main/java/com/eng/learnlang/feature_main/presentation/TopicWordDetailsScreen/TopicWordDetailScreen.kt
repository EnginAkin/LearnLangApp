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
           state.topicList?.let {topicList ->
               itemsIndexed(topicList.toList()){ i: Int, topicWordDay: TopicWordDay ->
                   TopicWordDayPost(
                      topicWordDay=topicWordDay,
                       onclickListener = {topicWordDay ->

                           navController.navigate(
                               Screen.TopicWordDetailContentScreen.route
                                       +"?categoryName=${topicWordDay.categoryName}" +
                                       "?day=${topicWordDay.wordDayCount}" +
                                       "?limit=${topicWordDay.totalWordCount}"

                           )
                       }
                   )

               }
           }
       }

   }

}