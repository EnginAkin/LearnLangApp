package com.eng.learnlang.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eng.learnlang.core.domain.model.TopicWordDay
import com.eng.learnlang.core.presentation.ui.theme.hintgray
import com.eng.learnlang.core.util.Screen


@Composable
fun TopicWordDayPost(
    topicWordDay: TopicWordDay,
    onclickListener: (TopicWordDay)->Unit={}
) {
    val categoryName = topicWordDay.categoryName
    val wordDayCount =topicWordDay.wordDayCount
    val learnedCountWord = topicWordDay.learnedCountWord
    val totalCountWord =topicWordDay.totalWordCount

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                if(learnedCountWord/totalCountWord<1){
                    onclickListener(topicWordDay)
                }else null
            }
            .background(color = if(learnedCountWord/totalCountWord==1)MaterialTheme.colors.onSecondary else hintgray)
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 15.dp,
                    top = 7.dp,
                    end = 15.dp,
                    bottom = 15.dp
                )
        ) {
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = categoryName, fontWeight = FontWeight.Bold,color = Color.White,style = TextStyle(fontSize = 24.sp))
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "Day ${wordDayCount.toString()}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = TextStyle(fontSize = 24.sp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row (modifier = Modifier.align(Alignment.End)){
                Text(
                    text = "$learnedCountWord/$totalCountWord",
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = (learnedCountWord.toFloat() / totalCountWord.toFloat()),
                backgroundColor = Color.LightGray,
                color = MaterialTheme.colors.primary
            )

        }
    }


}