package com.eng.learnlang.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.eng.learnlang.presentation.components.ScaffolForComp
import com.eng.learnlang.presentation.util.Screen

@Composable
fun ProfileScreen(
    navController: NavController
) {
    ScaffolForComp(
        navController = navController,
        modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "About Page")
            }
    }

}