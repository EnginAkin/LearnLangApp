package com.eng.learnlang.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.R
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.feature_test.presentation.test.TestViewModel
import com.eng.learnlang.util.speak

@Composable
fun TestContent(
    navController: NavController,
    CorrectAnswer: Word,
    allQuestion: List<Word>,
    onClik: (Word) -> Unit,
    testViewModel: TestViewModel= hiltViewModel()
) {
    val applicationContext = LocalContext.current
    var clickedCorreckButton = false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 100.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                IconButton(onClick = {
                    CorrectAnswer.word?.let { speak(it, applicationContext) }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ses_icon),
                        contentDescription = "ıcon for voice word",
                        Modifier.size(35.dp)
                    )
                }
            }
            Column(Modifier.weight(8f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = CorrectAnswer.word!!,
                    style = MaterialTheme.typography.h1,
                    color = Color.White
                )
            }

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 100.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Doğru Seçeneği Seçin", color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
            allQuestion.shuffled().forEachIndexed { index, word ->
                Button(
                    onClick = {
                        if(CorrectAnswer.equals(word)){
                            clickedCorreckButton=true
                        }
                        onClik(word)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = if(clickedCorreckButton) MaterialTheme.colors.primary else Color.White),
                    shape = RoundedCornerShape(30.dp),
                    border = BorderStroke(1.dp, color = MaterialTheme.colors.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Text(text = word.mean!!, color = if(clickedCorreckButton) Color.White else Color.Black, fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}

