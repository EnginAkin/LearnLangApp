package com.eng.learnlang.presentation.topic_word_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Sentence
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.components.StandartToolBar
import com.eng.learnlang.core.presentation.components.WordContent
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.feature_main.present.TopicWordDetailContentViewModel

@Composable
fun TopicWordDetailContentScreen(
    navController: NavController,
    viewmodel: TopicWordDetailContentViewModel = hiltViewModel(),
) {
    val state = viewmodel.state.value
    if (!state.isLoading) {
        Column(modifier = Modifier.fillMaxSize()) {
            StandartToolBar(
                navController = navController,
                showBackArrow = true,
                modifier = Modifier.fillMaxWidth(),
                navAction = {}) {
                Text(
                    text = state.wordList?.get(0)?.category?.categoryName ?:"",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                    Button(onClick = {
                        navController.navigate(Screen.TeachDetailScreen.route)
                    }, modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
                        Text(text = "Start Learning", style = MaterialTheme.typography.h2)
                        Icon(
                            imageVector = Icons.Default.SkipNext,
                            contentDescription = "next",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn() {
                if(state.wordList!=null){
                    state.wordList.forEachIndexed { index, word ->
                        item {
                            WordContent(
                                navController = navController,
                                word = word,
                                indexNumber = index + 1,
                                addlistClick = {

                                },
                                listenClickListener = {
                                }
                            )

                        }
                    }
                }
            }


        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            StandartToolBar(
                navController = navController,
                showBackArrow = true,
                modifier = Modifier.fillMaxWidth(),
                navAction = {}) {
                Text(
                    text = "Onceki Sayfa",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(modifier = Modifier.align(CenterHorizontally)){
                Text(
                    text ="Yüklenirken Sorun Yaşandı !! Daha sonra tekrar deneyiniz.",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                CircularProgressIndicator()
            }
        }
    }
}