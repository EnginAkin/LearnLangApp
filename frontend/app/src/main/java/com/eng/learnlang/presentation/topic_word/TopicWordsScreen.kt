package com.eng.learnlang.presentation.topic_word

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.domain.model.Category
import com.eng.learnlang.presentation.components.CategoryPost
import com.eng.learnlang.presentation.components.ScaffolForComp
import com.eng.learnlang.presentation.ui.theme.bottomGray

@Composable
fun TopicWords(
    navController: NavController,

) {
    val a1= Category(
        "A-1",
        "A1 level of English is essentially a native level. It allows for reading and writing of any type on any subject, nuanced expression of emotions and opinions, and active participation in any academic or professional setting. ... Can understand with ease virtually everything heard or read."
        ,100,10
    )
    val a2= Category(
        "A-2",categoryDescription = "Level A2 corresponds to basic users of the language, i.e. those able to communciate in everyday situations with commonly-used expressions and elementary vocabulary"
        ,120,12
    )
    val b1= Category(
        "B-2",categoryDescription = "English level B1 is the third level of English in the Common European Framework of Reference (CEFR), a definition of different language levels written by the Council of Europe. In everyday speech, this level would be called “intermediate”, and indeed, that is the official level descriptor in the CEFR.",
        190,19
    )

        CategoryPost(category = a2)
        CategoryPost(category = a1)
        CategoryPost(category = b1)



}