package com.eng.learnlang.presentation.TopicWordDetailsScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.eng.learnlang.domain.model.Category
import com.eng.learnlang.domain.model.TopicWordDay
import com.eng.learnlang.presentation.components.StandartToolBar
import com.eng.learnlang.presentation.components.TopicWordDayPost

@Composable
fun TopicWordDetailScreen(
    navController: NavController,
) {

    var listOfItems = listOf(
        TopicWordDay(
            categoryName = "A-1",
            0,
            learnedCountWord = 8
        ),
        TopicWordDay(
            categoryName = "A-2",
            1,
            learnedCountWord = 5
        ),
        TopicWordDay(
            categoryName = "B-1",
            2,
            learnedCountWord = 4
        ), TopicWordDay(
            categoryName = "B-2",
            3,
            learnedCountWord = 9
        ), TopicWordDay(
            categoryName = "C-1",
            4,
            learnedCountWord = 1
        ), TopicWordDay(
            categoryName = "C-2",
            5,
            learnedCountWord = 3
        ), TopicWordDay(
            categoryName = "D-1",
            6,
            learnedCountWord = 10
        )
    )
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
           itemsIndexed(listOfItems){ i: Int, topicWordDay: TopicWordDay ->
               TopicWordDayPost(
                   categoryName = topicWordDay.categoryName,
                   wordDayCount = topicWordDay.wordDayCount,
                   learnedCountWord = topicWordDay.learnedCountWord,
                   navController=navController
               )

           }
       }

   }

}