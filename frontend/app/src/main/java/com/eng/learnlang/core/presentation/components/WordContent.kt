package com.eng.learnlang.core.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.R
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.ui.theme.hintgray
import com.eng.learnlang.util.speak

@Composable
fun WordContent(
    word: Word,
    indexNumber: Int,
    addWordlistClick: (Long) -> Unit = {},
    addLearnedList: (Long) -> Unit = {},
    clickSpeakWord: (String) -> Unit = {},
) {
    val applicationContext = LocalContext.current
    val verifiedState = remember {
        mutableStateOf(word.verified)
    }
    var clicked = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .clickable {
                    clicked.value = !clicked.value
                }
                .border(width = 1.dp, color = Color.White, RoundedCornerShape(10.dp))
                .background(color = if (clicked.value) MaterialTheme.colors.onSurface else MaterialTheme.colors.onSecondary)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.padding(vertical = 9.dp)) {
                    Text(
                        text = "$indexNumber.${word.name} ",
                        style = MaterialTheme.typography.h2,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 9.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "verified",
                        tint = if (verifiedState.value) MaterialTheme.colors.primary else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = if (clicked.value) Icons.Default.ArrowDropUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "verified",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
        if (clicked.value) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                ) {
                    Column() {
                        Image(
                            painter = painterResource(id = R.drawable.ic_turkey_flag),
                            contentDescription = "turkey flag",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            word.mean?.let { mean ->
                                Text(
                                    modifier = Modifier.padding(top = 5.dp),
                                    text = mean,
                                    style = MaterialTheme.typography.body1,
                                    color = Color.White,
                                )
                            }
                            IconButton(
                                onClick = {
                                    word.name?.let { wordName ->
                                        clickSpeakWord(wordName)
                                    }
                                },
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_ses_icon),
                                    contentDescription = "icon ses",
                                    Modifier.size(24.dp),
                                    tint = Color.White
                                )
                            }
                        }
                        if (word.exampleSentence!!.get(0) != null) {
                            Divider(color = Color.White, thickness = 0.8.dp)
                            Column(modifier = Modifier.padding(10.dp)) {
                                word.exampleSentence?.get(0)?.sentenceEN?.let {
                                    Text(
                                        text = word.exampleSentence[0].sentenceEN!!,
                                        style = MaterialTheme.typography.body1,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                ) {
                    Column(
                        Modifier.weight(1f)
                    ) {
                        Button(onClick = {
                            word.id?.let { addWordlistClick(it.toLong()) }
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Kelime listeme Ekle",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                    Column(
                        Modifier.weight(1f)
                    ) {
                        Button(
                            enabled = !verifiedState.value,
                            onClick = {
                                verifiedState.value=true
                                word.id?.let { addLearnedList(it.toLong()) }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    0.dp,
                                    hintgray, RoundedCornerShape(0.dp)
                                )
                        ) {
                           if(!verifiedState.value){
                               Text(
                                   text = "Biliyorum",
                                   color = Color.White,
                                   fontWeight = FontWeight.Bold
                               )
                           }else{
                               Text(
                                   text = "Biliyorum",
                                   color = Color.Gray,
                                   fontWeight = FontWeight.Bold,

                               )
                           }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color.White, thickness = 0.8.dp)
            }
        }
    }
}
/*
 Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                if(learnedCountWord/totalCountWord<1){
                    navController.navigate(Screen.TopicWordDetailContentScreen.route)
                }else null
            }
            .background(color = if(learnedCountWord/totalCountWord==1)MaterialTheme.colors.onSecondary else hintgray)
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 15.dp,
                    top = 7.dp,
                    end = 15.dp,
                    bottom = 15.dp
                )
        ) {
 */