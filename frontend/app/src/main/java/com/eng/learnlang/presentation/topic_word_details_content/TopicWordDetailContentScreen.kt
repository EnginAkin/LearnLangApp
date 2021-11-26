package com.eng.learnlang.presentation.topic_word_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.domain.model.Category
import com.eng.learnlang.domain.model.TopicWordDay
import com.eng.learnlang.domain.model.Word
import com.eng.learnlang.presentation.components.StandartToolBar
import com.eng.learnlang.presentation.components.WordContent

@Composable
fun TopicWordDetailContentScreen(
    navController: NavController,
    wordList : List<Word> = listOf(
            Word(
                word = "Hi",
                mean = "Merhaba",
                category = Category("A-1","xx",100,10)
            )
    ),
) {

  Column(modifier = Modifier.fillMaxSize()) {
      
      StandartToolBar(
          navController = navController,
          showBackArrow = true,
          modifier = Modifier.fillMaxWidth(),
          navAction = {}){
          Text(text = wordList?.get(0)?.category!!.categoryName,color=Color.White,fontWeight = FontWeight.Bold)
      }
      Spacer(modifier = Modifier.height(20.dp))
      Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
          Row(modifier = Modifier.fillMaxWidth(0.6f)) {
              Button(onClick = { /*TODO*/ },modifier=Modifier.clip(RoundedCornerShape(20.dp))) {
                  Text(text = "Start Learning ",style = MaterialTheme.typography.h2)
                  Icon(imageVector= Icons.Default.SkipNext, contentDescription ="next" ,tint = Color.White,modifier = Modifier.size(50.dp))
              }
          }
      }
      Spacer(modifier = Modifier.height(20.dp))
      LazyColumn( ){
          var verifed= false
          items(10){i->
              if(i==3 || i==6 ) verifed=true
              WordContent(verified = verifed,navController = navController, word = Word(word = "Hi",mean = "Merhaba",category = Category("A-1","xx",100,10)), indexNumber = i+1)
                verifed=false
          }
      }


  }
}