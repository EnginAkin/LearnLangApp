package com.eng.learnlang.feature_auth.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.R
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.core.util.Constants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SplashScreen(
    navController: NavController,
    dispatcher: CoroutineDispatcher=Dispatchers.Main,
    viewmodel : SplashViewModel= hiltViewModel()
){
    val scale = remember{
        Animatable(0f)
    }
    val overshootInterpolator= remember {
        OvershootInterpolator(2f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    overshootInterpolator.getInterpolation(it)
                }

            )
        )
    }

    LaunchedEffect(key1 = true ){
        viewmodel.eventFlow.collectLatest {
            when(it){
                is UiEvent.Navigate ->{
                navController.popBackStack()
                    navController.navigate(it.route)
                }
                else ->{
                    Unit
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Image(modifier = Modifier.scale(scale.value),painter = painterResource(id = R.drawable.ic_logo), contentDescription = "LearnLang Logo")
    }
}