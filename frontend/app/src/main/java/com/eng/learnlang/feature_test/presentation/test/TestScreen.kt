package com.eng.learnlang.presentation.test_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Sentence
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.components.StandartToolBar
import com.eng.learnlang.core.presentation.components.TestContent
import com.eng.learnlang.feature_test.presentation.teach_detail.TeachViewModel
import com.eng.learnlang.feature_test.presentation.test.TestViewModel

@Composable
fun TestScreen(
    wordList: List<Word> = listOf(
        Word(
            name = "Hi",
            mean = "Merhaba",
            description = "used as an informal way of saying \"hello\"",
            category = Category("A-1","xx",100,10),
            exampleSentence = listOf(
                Sentence(sentenceEN = "Hi How are you today ? ",sentenceTR = "Hey merhaba bugün nasılsın? ")
            ),
            verified = true
        ),
        Word(
            name = "What",
            mean = "Ne",
            description = "a spoken or written account of a person, object, or event.",
            category = Category("A-1","xx",100,10),
            exampleSentence = listOf(
                Sentence(sentenceEN = "What is your name",sentenceTR = "Adın ne? ")
            ),

            ),
        Word(
            name = "Mean",
            mean = "Anlam",
            category = Category("A-1","xx",100,10),
            exampleSentence = listOf(
                Sentence(sentenceEN = "what do you mean ?",sentenceTR = "ne diyorsun? ")
            ), verified = true
        ),
        Word(
            name = "Weather",
            mean = "Hava",
            category = Category("A-1","xx",100,10),
            exampleSentence = listOf(
                Sentence(sentenceEN = "Weather is so bad today so I cant go out home",sentenceTR = "Bugun hava çok kötü olduğu için dışarı çıkamıyorum? ")
            )
        ),
    ) ,
    navController: NavController,
    viewModel: TestViewModel = hiltViewModel()
) {
    val state=viewModel.state.value
    var word = state.wordList?.get(state.currentIndex)
    var currentIndex = state.currentIndex
    var isDone = (currentIndex+1)/ (state.wordList?.size ?: 0) ==1
    var progressBarCount=(currentIndex+1).toFloat()/state.wordList?.size!!.toFloat()


    Column(modifier = Modifier.fillMaxSize()) {
        StandartToolBar(
            backgroundColor = MaterialTheme.colors.background,
            navController = navController,
            imageVector = Icons.Default.Close,
            showBackArrow = true
        ) {
            LinearProgressIndicator(progress = progressBarCount)
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()){
        if (word != null) {

            TestContent(navController = navController, CorrectAnswer = word,allQuestion = wordList, onClik = {})
        }

    }

}