package com.eng.learnlang.feature_mywordlist.presentation.wordlist_main_feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Sentence
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.components.MyListWordContent
import com.eng.learnlang.core.presentation.components.StandartToolBar
import com.eng.learnlang.core.util.Screen


@Composable
fun MyWordListFeedScreen(
    navController: NavController,
    viewmodel: MyWordListViewModel = hiltViewModel()
) {
    val state = viewmodel.state.value
    Column(modifier = Modifier.fillMaxSize()) {
        StandartToolBar(navController = navController) {
            Text(text = "My Word List", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                             navController.navigate(Screen.MyWordListDetailScreen.route)
            }, modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
                Text(text = "Start Test", style = MaterialTheme.typography.h2)
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "next",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn() {
            state.wordList?.forEachIndexed { index, word ->

                item {
                    MyListWordContent(
                        word = word,
                        indexNumber = index + 1,
                        listenClickListener = {
                            viewmodel.onEvent(MyWordListViewModel.MyWordListEvent.ListenWord(it))
                        }
                    )

                }
            }
        }


    }
}