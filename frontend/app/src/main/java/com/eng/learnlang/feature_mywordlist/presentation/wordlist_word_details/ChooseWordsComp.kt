package com.eng.learnlang.feature_mywordlist.presentation.wordlist_word_details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.ui.theme.hintgray

@Composable
fun ChooseWordsComp(
    indexNumber:Int=0,
    word : Word,
    onClickChoose : (Long)->Unit,
    selected : Boolean =false
) {

    var clicked = remember {
        mutableStateOf(selected)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.onSecondary)
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(10.dp))
            .clickable {
                clicked.value = !clicked.value
                word.id?.let { onClickChoose(it.toLong()) }
            }

    ) {
        Row(modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 12.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(
                    text = "$indexNumber.${word.name} ",
                    style = MaterialTheme.typography.h2,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(verticalArrangement = Arrangement.Center) {
                Button(
                    modifier = Modifier
                        .width(35.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = if(clicked.value)MaterialTheme.colors.primary else Color.White)
                , onClick = {
                    clicked.value=!clicked.value
                        word.id?.let { onClickChoose(it.toLong())}
                    }) {

                }
            }
        }

    }


}