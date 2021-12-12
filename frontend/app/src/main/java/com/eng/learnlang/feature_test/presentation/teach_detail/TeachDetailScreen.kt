package com.eng.learnlang.presentation.test_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Sentence
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.components.StandartToolBar
import com.eng.learnlang.core.presentation.components.TeachContent
import com.eng.learnlang.feature_test.presentation.teach_detail.TestDetailviewModel
import com.eng.learnlang.core.util.Screen

@Composable
fun TeachDetailScreen(
    list: List<Word> =  listOf(),
    navController: NavController,
    wordList : List<Word> = listOf(
        Word(
            name = "Hi",
            mean = "Merhaba",
            description = "used as an informal way of saying \"hello\"",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Hi How are you today ? ",sentenceTR = "Hey merhaba bugün nasılsın? ")
            ),
            verified = true
        ),
        Word(
            name = "What",
            mean = "Ne",
            description = "a spoken or written account of a person, object, or event.",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "What is your name",sentenceTR = "Adın ne? ")
            ),

            ),
        Word(
            name = "Mean",
            mean = "Anlam",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "what do you mean ?",sentenceTR = "ne diyorsun? ")
            ), verified = true
        ),
        Word(
            name = "Weather",
            mean = "Hava",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Weather is so bad today so I cant go out home",sentenceTR = "Bugun hava çok kötü olduğu için dışarı çıkamıyorum? ")
            )
        ),
    ),
    viewModel: TestDetailviewModel = hiltViewModel()
) {
    println("current index in viewmodel : ${viewModel.currentIndexTeach.value}")
    var word =wordList.get(viewModel.currentIndexTeach.value)
    var currentIndex = viewModel.currentIndexTeach.value
    var isDone = (currentIndex+1)/wordList.size ==1
    var progressBarCount=(currentIndex+1).toFloat()/wordList.size.toFloat()
    Column(modifier = Modifier.fillMaxSize()) {
        StandartToolBar(backgroundColor = MaterialTheme.colors.background,navController = navController, imageVector = Icons.Default.Close, showBackArrow = true){
            LinearProgressIndicator(progress = progressBarCount)
        }
        Box(modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center){
            TeachContent(
                word = word,
                isDoneTeach = isDone,
                clickedButtonNext = {
                    viewModel.setCurrenIndexTeach(viewModel.currentIndexTeach.value+1)
                },
                clickedButtonTest = {
                    navController.navigate(Screen.TestDetailScreen.route)
                }
            )
        }
        
    }
}
