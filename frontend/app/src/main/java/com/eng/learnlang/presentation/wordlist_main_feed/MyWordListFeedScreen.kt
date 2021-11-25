package com.eng.learnlang.presentation.wordlist_main_feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.eng.learnlang.presentation.components.ScaffolForComp


@Composable
fun MyWordListFeedScreen(
    navController: NavController
) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = "My Word list  Page")
        }


}