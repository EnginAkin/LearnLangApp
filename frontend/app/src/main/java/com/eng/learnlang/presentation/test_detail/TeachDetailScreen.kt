package com.eng.learnlang.presentation.test_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.VoiceChat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.R
import com.eng.learnlang.domain.model.Category
import com.eng.learnlang.domain.model.Sentence
import com.eng.learnlang.domain.model.Word
import com.eng.learnlang.presentation.components.StandartToolBar
import com.eng.learnlang.presentation.components.TeachContent
import com.eng.learnlang.presentation.util.Screen

@Composable
fun TeachDetailScreen(
    list: List<Word> =  listOf(),
    navController: NavController,
    wordList : List<Word> = listOf(
        Word(
            word = "Hi",
            mean = "Merhaba",
            description = "used as an informal way of saying \"hello\"",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Hi How are you today ? ",sentenceTR = "Hey merhaba bugün nasılsın? ")
            ),
            verified = true
        ),
        Word(
            word = "What",
            mean = "Ne",
            description = "a spoken or written account of a person, object, or event.",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "What is your name",sentenceTR = "Adın ne? ")
            ),

            ),
        Word(
            word = "Mean",
            mean = "Anlam",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "what do you mean ?",sentenceTR = "ne diyorsun? ")
            ), verified = true
        ),
        Word(
            word = "Weather",
            mean = "Hava",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Weather is so bad today so I cant go out home",sentenceTR = "Bugun hava çok kötü olduğu için dışarı çıkamıyorum? ")
            )
        ),
        Word(
            word = "paper",
            mean = "kağıt",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "We'll need pens, glue, and some paper.",sentenceTR = "kaleme yapıştırıcıya ve kağıda ihtiyacımız olacak "),
            )
        ),
        Word(
            word = "coat",
            mean = "kaban/palto",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "He was wearing a coat and tie.",sentenceTR = "kot ve kravat giymişti "),
            ), verified = true
        ),
        Word(
            word = "Weather",
            mean = "Hava",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Weather is so bad today so I cant go out home",sentenceTR = "Bugun hava çok kötü olduğu için dışarı çıkamıyorum? ")
            )
        ),
        Word(
            word = "paper",
            mean = "kağıt",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "We'll need pens, glue, and some paper.",sentenceTR = "kaleme yapıştırıcıya ve kağıda ihtiyacımız olacak "),
            )
        ),
        Word(
            word = "coat",
            mean = "kaban/palto",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "He was wearing a coat and tie.",sentenceTR = "kot ve kravat giymişti "),
            ), verified = true
        ),
        Word(
            word = "Weather",
            mean = "Hava",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "Weather is so bad today so I cant go out home",sentenceTR = "Bugun hava çok kötü olduğu için dışarı çıkamıyorum? ")
            )
        ),
        Word(
            word = "paper",
            mean = "kağıt",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "We'll need pens, glue, and some paper.",sentenceTR = "kaleme yapıştırıcıya ve kağıda ihtiyacımız olacak "),
            )
        ),
        Word(
            word = "coat",
            mean = "kaban/palto",
            category = Category("A-1","xx",100,10),
            examplaSenctence = listOf(
                Sentence(sentenceEN = "He was wearing a coat and tie.",sentenceTR = "kot ve kravat giymişti "),
            ), verified = true
        ),
    ),
    viewModel: TestDetailviewModel= hiltViewModel()
) {
    println("current index in viewmodel : ${viewModel.currentIndexTeach.value}")
    var word =wordList.get(viewModel.currentIndexTeach.value)
    var currentIndex = viewModel.currentIndexTeach.value
    var isDone = (currentIndex+1)/wordList.size ==1
    Column(modifier = Modifier.fillMaxSize()) {
        StandartToolBar(backgroundColor = MaterialTheme.colors.background,navController = navController, imageVector = Icons.Default.Close, showBackArrow = true){
            LinearProgressIndicator(progress = ((currentIndex+1).toFloat()/wordList.size.toFloat()))
        }
        Box(modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center){
            TeachContent(
                word = word,
                isDoneTeach = isDone,
                clickedButtonNext = {
                    viewModel.setCurrenIndexTeach(viewModel.currentIndexTeach.value+1)
                },
                clickedButtonTest = {
                    navController.navigate(Screen.TestDetailScreen.route)
                }
            )
        }
        
    }
}
