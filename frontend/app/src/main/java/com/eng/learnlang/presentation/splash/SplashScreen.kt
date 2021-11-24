package com.eng.learnlang.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.R
import com.eng.learnlang.presentation.util.Screen
import com.eng.learnlang.util.Constants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController,
    dispatcher: CoroutineDispatcher=Dispatchers.Main
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
        delay(Constants.SPLASH_SCREEN_DURATİON)
        navController.popBackStack()
        navController.navigate(Screen.LoginScreen.route)
    }


    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Image(modifier = Modifier.scale(scale.value),painter = painterResource(id = R.drawable.ic_logo), contentDescription = "LearnLang Logo")
    }
}