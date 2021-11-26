package com.eng.learnlang.presentation.topic_word

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.domain.model.Category
import com.eng.learnlang.presentation.components.CategoryPost
import com.eng.learnlang.presentation.components.ScaffolForComp
import com.eng.learnlang.presentation.components.StandartToolBar
import com.eng.learnlang.presentation.ui.theme.bottomGray
import com.eng.learnlang.presentation.util.Screen

@Composable
fun TopicWords(
    navController: NavController,

    ) {

    val a1 = Category(
        "A-1",
        "A1 level of English is essentially a native level. It allows for reading and writing of any type on any subject, nuanced expression of emotions and opinions, and active participation in any academic or professional setting. ... Can understand with ease virtually everything heard or read.",
        100,
        10
    )
    val a2 = Category(
        "A-2",
        categoryDescription = "Level A2 corresponds to basic users of the language, i.e. those able to communciate in everyday situations with commonly-used expressions and elementary vocabulary",
        120,
        12
    )
    val b1 = Category(
        "B-1",
        categoryDescription = "English level B1 is the third level of English in the Common European Framework of Reference (CEFR), a definition of different language levels written by the Council of Europe. In everyday speech, this level would be called “intermediate”, and indeed, that is the official level descriptor in the CEFR.",
        190,
        19
    )
    val b2 = Category(
        "B-2",
        categoryDescription = "English level B2 is the third level of English in the Common European Framework of Reference (CEFR), a definition of different language levels written by the Council of Europe. In everyday speech, this level would be called “intermediate”, and indeed, that is the official level descriptor in the CEFR.",
        350,
        35
    )
    var listOfCategory = listOf(a1,a2,b1,b2)
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandartToolBar(
            navController = navController,
            title = {
                Text(text = "Word Categories", fontWeight = FontWeight.Bold, color = Color.White)
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
            navAction = {
                IconButton(onClick = {
                    //navController.navigate(Screen.SearchScreen.route)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "add",
                        tint = Color.White
                    )
                }
            }
        )

        LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = 50.dp)){
            listOfCategory.forEachIndexed { index, category ->
                item {
                    CategoryPost(category = category, navController = navController) {
                        navController.navigate(Screen.TopicWordDetailScreen.route)

                    }
                }
            }
        }

    }


}