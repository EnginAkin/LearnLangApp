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
import androidx.navigation.NavController
import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Sentence
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.components.MyListWordContent
import com.eng.learnlang.core.presentation.components.StandartToolBar


@Composable
fun MyWordListFeedScreen(
    navController: NavController,
    wordList : List<Word> = listOf(
        Word(
            name = "Hi",
            mean = "Merhaba",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Hi How are you today ? ",sentenceTR = "Hey merhaba bugün nasılsın? ")
            ),
            verified = true
        ),
        Word(
            name = "What",
            mean = "Ne",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "What is your name",sentenceTR = "Adın ne? ")
            ), verified = true

            ),
        Word(
            name = "Mean",
            mean = "Anlam",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "what do you mean ?",sentenceTR = "ne diyorsun? ")
            ), verified = true
        ),
        Word(
            name = "Weather",
            mean = "Hava",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Weather is so bad today so I cant go out home",sentenceTR = "Bugun hava çok kötü olduğu için dışarı çıkamıyorum? ")
            ), verified = true
        ),
        Word(
            name = "paper",
            mean = "kağıt",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "We'll need pens, glue, and some paper.",sentenceTR = "kaleme yapıştırıcıya ve kağıda ihtiyacımız olacak "),
            ), verified = true
        ),
        Word(
            name = "coat",
            mean = "kaban/palto",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "He was wearing a coat and tie.",sentenceTR = "kot ve kravat giymişti "),
            ), verified = true
        ),
        Word(
            name = "Weather",
            mean = "Hava",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Weather is so bad today so I cant go out home",sentenceTR = "Bugun hava çok kötü olduğu için dışarı çıkamıyorum? ")
            ), verified = true
        ),
        Word(
            name = "paper",
            mean = "kağıt",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "We'll need pens, glue, and some paper.",sentenceTR = "kaleme yapıştırıcıya ve kağıda ihtiyacımız olacak "),
            ), verified = true
        ),
        Word(
            name = "coat",
            mean = "kaban/palto",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "He was wearing a coat and tie.",sentenceTR = "kot ve kravat giymişti "),
            ), verified = true
        ),
        Word(
            name = "Weather",
            mean = "Hava",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Weather is so bad today so I cant go out home",sentenceTR = "Bugun hava çok kötü olduğu için dışarı çıkamıyorum? ")
            ), verified = true
        ),
        Word(
            name = "paper",
            mean = "kağıt",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "We'll need pens, glue, and some paper.",sentenceTR = "kaleme yapıştırıcıya ve kağıda ihtiyacımız olacak "),
            ), verified = true
        ),
        Word(
            name = "coat",
            mean = "kaban/palto",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "He was wearing a coat and tie.",sentenceTR = "kot ve kravat giymişti "),
            ), verified = true
        ),
    ),
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandartToolBar(navController = navController){
            Text(text = "My Word List",color= Color.White,fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                Button(onClick = { /*TODO*/ },modifier=Modifier.clip(RoundedCornerShape(20.dp))) {
                    Text(text = "Start Test",style = MaterialTheme.typography.h2)
                    Icon(imageVector= Icons.Default.SkipNext, contentDescription ="next" ,tint = Color.White,modifier = Modifier.size(40.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn( ){
            wordList.forEachIndexed { index, word ->

                item {
                    MyListWordContent(
                        word = word,
                        indexNumber = index + 1,
                        addlistClick = {

                        },
                        listenClickListener = {

                        }
                    )

                }
            }
        }


    }
}