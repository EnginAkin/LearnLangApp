package com.eng.learnlang.presentation.library_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.github.skgmn.composetooltip.AnchorEdge
import com.github.skgmn.composetooltip.Tooltip
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun LibraryDetailScreen(
    scaffoldState: ScaffoldState,
) {
    val stringSiir="Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n" +
            "\"Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs.\"\n"


   val StringArray:List<String> = stringSiir.split(" ")
    val clicked = remember {
        mutableStateOf(false)
    }
    val clickedString = remember {
        mutableStateOf("")
    }
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        item {
            FlowRow(
                mainAxisAlignment = MainAxisAlignment.Start,
                mainAxisSize = SizeMode.Expand,
                crossAxisSpacing = 12.dp,
                mainAxisSpacing = 8.dp
            ) {
                StringArray.forEach { hashTag ->
                    Text(
                        text = hashTag,
                        color = Color.Black,
                        modifier = Modifier.clickable {
                            clicked.value=true
                            clickedString.value=hashTag }
                    )
                }
            }

        }
    }


}