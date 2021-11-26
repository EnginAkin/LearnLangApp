package com.eng.learnlang.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.R
import com.eng.learnlang.domain.model.Word

@Composable
fun WordContent(
    navController: NavController,
    word: Word,
    verified:Boolean =false,
    indexNumber: Int ,
    addlistClick : (Word) ->Unit={},
    listenClickListener : () -> Unit={}
) {
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),horizontalArrangement = Arrangement.SpaceBetween) {
                Row(modifier = Modifier.padding(vertical = 9.dp)) {
                    Text(text = "$indexNumber.Selam ",style = MaterialTheme.typography.h2,color = Color.White,fontWeight = FontWeight.Bold)
                }

                Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center,modifier = Modifier.padding(vertical = 9.dp)) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "verified",
                        tint = if(verified) MaterialTheme.colors.primary else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(imageVector= if(clicked.value) Icons.Default.ArrowDropUp else Icons.Default.KeyboardArrowDown, contentDescription = "verified",modifier = Modifier.size(20.dp))

                }
            }



        }
        if(clicked.value){
            Box(modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.White)
            ){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)) {
                    Column(modifier = Modifier.fillMaxWidth(0.2f)) {
                        // bayrak iconu
                        Icon(painter = painterResource(id = R.drawable.ic_turkey_flag), contentDescription ="turkey flag" ,modifier = Modifier.padding(12.dp))
                    }
                    Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                        Column(modifier = Modifier.padding(15.dp)) {
                            Text(text = word.word,style = MaterialTheme.typography.h2,color = Color.White,fontWeight = FontWeight.Bold)
                        }

                        Column(modifier = Modifier.padding(10.dp)) {
                            word.examplaSenctence?.get(0)?.sentenceEN?.let {
                                    Text(text = word.examplaSenctence.get(0).sentenceEN!!,style = MaterialTheme.typography.h2,color = Color.White,fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column (modifier=Modifier.fillMaxWidth()){
                        Button(onClick = {
                            addlistClick(word)
                        }) {
                            Text(text = "Listeye Ekle",style = MaterialTheme.typography.h2,color = Color.White,fontWeight = FontWeight.Bold)
                        }
                    }
                    Column (modifier=Modifier.fillMaxWidth()){
                        Button(onClick = listenClickListener) {
                            Text(text = "Dinle",style = MaterialTheme.typography.h2,color = Color.White,fontWeight = FontWeight.Bold)
                        }
                    }

                }
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