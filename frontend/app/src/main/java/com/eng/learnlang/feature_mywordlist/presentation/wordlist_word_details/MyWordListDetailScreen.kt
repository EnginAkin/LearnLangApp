package com.eng.learnlang.feature_mywordlist.presentation.wordlist_word_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.R
import com.eng.learnlang.core.presentation.components.StandartTextField
import com.eng.learnlang.core.presentation.components.StandartToolBar
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.feature_mywordlist.presentation.wordlist_main_feed.MyWordListViewModel
import kotlinx.coroutines.flow.collectLatest
import okhttp3.internal.wait

@Composable
fun MyWordListDetailScreen(
    navController: NavController,
    viewmodel: WordListDetailViewModel = hiltViewModel()
) {
    val state = viewmodel.state.value

    LaunchedEffect(key1 = true ){
        viewmodel.eventFlow.collectLatest { event ->
            when(event){
                is UiEvent.Navigate ->{
                    navController.navigate(event.route)
                }
            }

        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        StandartToolBar(navController = navController, navAction = {
            IconButton(onClick = {
               viewmodel.onEvent(WordListDetailViewModel.MyWordListDetailEvent.clickedNextTest)
            }) {
               Icon(
                   painter = painterResource(id = R.drawable.ic_startup),
                   contentDescription = "Next",
                   tint = MaterialTheme.colors.primary
               )
            }
        }) {
            Text(text = "Choose words for test", color = Color.White, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Column(modifier=Modifier.fillMaxWidth()) {
            StandartTextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White),
                text = state.text,
                hint = "Search Word",
                onValueChange = {
                viewmodel.onEvent(WordListDetailViewModel.MyWordListDetailEvent.enteredTextForSearch(it))
            } )
        }


        Spacer(modifier = Modifier.height(50.dp))
        LazyColumn() {
            state.wordList?.forEachIndexed { index, word ->
                var selected= false
                if( viewmodel.iselected(word.id!!.toLong()) ) selected = true
                item {
                    ChooseWordsComp(
                        indexNumber = index,
                        selected =selected ,
                        word = word,
                        onClickChoose = {
                            viewmodel.onEvent(
                                WordListDetailViewModel.MyWordListDetailEvent.clickedWordContentForTested(
                                    it
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

    }

}