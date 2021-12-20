package com.eng.learnlang.presentation.test_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
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
import com.eng.learnlang.core.presentation.components.TestContent
import com.eng.learnlang.feature_test.presentation.teach_detail.TeachViewModel
import com.eng.learnlang.feature_test.presentation.test.TestViewModel

@Composable
fun TestScreen(
    navController: NavController,
    viewModel: TestViewModel = hiltViewModel()
) {
    val state=viewModel.state.value
    if(state.wordList!=null){
        var word = state.wordList!!.get(state.currentIndex)
        var currentIndex = state.currentIndex
        var isDone = (currentIndex+1)/ (state.wordList?.size) ==1
        var progressBarCount=(currentIndex+1).toFloat()/state.wordList.size.toFloat()

        println("word : $word")
        println("current index  : $currentIndex")
        println("is done: $isDone")
        println("progressbarCount: $progressBarCount")

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
                var wrongList = viewModel.setWrongWordList(word)
                TestContent( CorrectAnswer = word,allQuestion = wrongList, onClik = {

                })
            }

        }
    }
    if(state.isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }

}