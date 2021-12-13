package com.eng.learnlang.core.presentation.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eng.learnlang.R
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.util.speak


@Composable
fun TeachContent(
    word: Word,
    isDoneTeach : Boolean= false,
    clickedButtonNext : ()->Unit={},
    clickedButtonTest : ()->Unit={},


){
    val applicationContext = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .clip(RoundedCornerShape(30.dp))
        .background(MaterialTheme.colors.surface)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Column (Modifier.weight(8f)){
                Text(text = word.name!!,fontSize = 24.sp, modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.White)
            }
            Column (Modifier.weight(2f)){
                IconButton(onClick = {
                    word.name?.let { speak(it,applicationContext) }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ses_icon),
                        contentDescription = "ıcon for voice word"
                    )
                }
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding( 10.dp)) {
            Text(text = "Anlam :  "+word.mean!!, textAlign = TextAlign.Start, style = MaterialTheme.typography.body1, color = Color.White)
        }
        if(!word.description.isNullOrBlank()){
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.White, thickness = 1.dp)
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding( 10.dp)) {
                Text(text = "Tanım :  "+word.description!!, textAlign = TextAlign.Start, style = MaterialTheme.typography.body1, color = Color.White)
            }
        }
        if (word.exampleSentence?.size!! >0){
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.White, thickness = 1.dp)
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding( 10.dp)) {
                Text(text = "Örnek :  "+ word.exampleSentence?.get(0)!!.sentenceEN, textAlign = TextAlign.Start, style = MaterialTheme.typography.body1, color = Color.White)
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp), horizontalArrangement = Arrangement.End) {
           if(isDoneTeach){
               Button(onClick = {
                   clickedButtonTest()
               }) {
                   Text(text = "Teste Geç", color = Color.White)
               }
           }
            else{
               Button(onClick = {
                   clickedButtonNext()
               }) {
                   Text(text = "Devam Et", color = Color.White)
               }
           }
        }

    }

}