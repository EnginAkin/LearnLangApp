package com.eng.learnlang.core.presentation.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eng.learnlang.R
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.util.speak

@Composable
fun MyListWordContent(
    word: Word,
    indexNumber: Int ,
    addlistClick : (Word) ->Unit={},
    listenClickListener : () -> Unit={}
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
                    clicked.value= !clicked.value
                }
                .border(width = 1.dp, color = Color.White, RoundedCornerShape(10.dp))
                .background(color = if (clicked.value) MaterialTheme.colors.onSurface else MaterialTheme.colors.onSecondary)


        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),horizontalArrangement = Arrangement.SpaceBetween) {
                Row(modifier = Modifier.padding(vertical = 9.dp)) {
                    Text(text = "$indexNumber.${word.name} ",style = MaterialTheme.typography.h2,color = Color.White,fontWeight = FontWeight.Bold)
                }

                Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center,modifier = Modifier.padding(vertical = 9.dp)) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "verified",
                        tint = if(verifiedState.value) MaterialTheme.colors.primary else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(imageVector= if(clicked.value) Icons.Default.ArrowDropUp else Icons.Default.KeyboardArrowDown, contentDescription = "verified",modifier = Modifier.size(30.dp))

                }
            }



        }
        if(clicked.value){
            println("${word.name} clicked value =" +clicked.value)
            word.name?.let { speak(it,applicationContext = applicationContext) }
            Column(modifier = Modifier
                .fillMaxWidth()
            ){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)) {
                    Column() {
                        // bayrak iconu
                        Image(painter = painterResource(id = R.drawable.ic_turkey_flag), contentDescription = "turkey flag",modifier = Modifier.padding(12.dp) )
                    }
                    Column {
                        Column(modifier = Modifier.padding(15.dp)) {
                            word.mean?.let { Text(text = it,style = MaterialTheme.typography.body1,color = Color.White,) }
                        }
                        Divider(color = Color.White,thickness = 0.8.dp)
                        Column(modifier = Modifier.padding(10.dp)) {
                            word.exampleSentence?.get(0)?.sentenceEN?.let {
                                    Text(text = word.exampleSentence[0].sentenceEN!!,style = MaterialTheme.typography.body1,color = Color.White)
                            }
                        }
                    }

                }
                Spacer(modifier=Modifier.height(10.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color.White,thickness = 0.8.dp)

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