package com.eng.learnlang.presentation. test_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.core.presentation.components.StandartToolBar
import com.eng.learnlang.core.presentation.components.TeachContent
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.feature_test.presentation.teach_detail.TeachViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TeachDetailScreen(
    navController: NavController,
    viewModel: TeachViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is UiEvent.Navigate ->{
                    navController.navigate(event.route)
                }
            }

        }
    }

    if(viewModel.state.value.wordList!=null){
        val state = viewModel.state.value
        var word = state.wordList?.get(state.currentIndex)
        var currentIndex = state.currentIndex
        var isDone = (currentIndex + 1) / (state.wordList?.size ?: 0) == 1
        var progressBarCount = (currentIndex + 1).toFloat() / (state.wordList?.size?.toFloat() ?: 0F)
        Column(modifier = Modifier.fillMaxSize()) {
            StandartToolBar(
                backgroundColor = MaterialTheme.colors.background,
                navController = navController,
                imageVector = Icons.Default.Close,
                showBackArrow = true
            ) {
                LinearProgressIndicator(progress = progressBarCount)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                if (word != null) {
                    TeachContent(
                        word = word,
                        isDoneTeach = isDone,
                        clickedButtonNext = {
                            viewModel.onEvent(TeachViewModel.TeachEvent.LoadNext)
                        },
                        clickedButtonTest = {
                            viewModel.onEvent(TeachViewModel.TeachEvent.LoadTest)
                        }
                    )
                }
            }

        }
    }
    if(viewModel.state.value.isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }
}
