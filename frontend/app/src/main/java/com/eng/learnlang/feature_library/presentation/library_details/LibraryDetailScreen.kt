package com.eng.learnlang.feature_library.presentation.library_details

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.delay

@Composable
fun LibraryDetailScreen(
    scaffoldState: ScaffoldState,
    libraryDetailViewModel: LibraryDetailViewModel= hiltViewModel()
) {
    val stringSiir =
        "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs" +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs." +
                "Jetpack compose is a modern Android UI toolkit introduced by Google. It simplifies the app development process and speeds it up. With Jetpack Compose, you can write less code compared to the current view building approach – which also means less potential bugs."


    val state = libraryDetailViewModel.state.value
    val StringArray: List<String> = stringSiir.split(" ")

    val clickedString = remember {
        mutableStateOf("")
    }
    val showTooltip = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) { scrollState.animateScrollTo(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCCCBCB))
            .verticalScroll(state = scrollState)
            .padding(10.dp),
    ) {
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
                        state.showTooltip=true
                        state.clickedString=hashTag
                    }
                )
            }
        }


    }
    var mean = "Mean"
    if (showTooltip.value) {
        TooltipForLibrary(expanded = showTooltip) {
            if (clickedString.value == "process") mean = "İşlemek"

            ConverWordInfo(
                word = clickedString.value,
                mean = mean,
                closeClicked = {
                    showTooltip.value = false
                },
                onclick = {

                }
            )
        }
    }

}

private val TooltipPadding = 16.dp
private val TooltipPopupProperties = PopupProperties(focusable = true)
private val TooltipOffset = DpOffset(0.dp, 0.dp)
private const val InTransitionDuration = 64
private const val OutTransitionDuration = 240
private const val TooltipTimeout = 5_000L - OutTransitionDuration
@Composable
fun TooltipForLibrary(
    expanded: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    timeoutMillis: Long = TooltipTimeout,
    backgroundColor: Color = Color.Black,
    offset: DpOffset = TooltipOffset,
    properties: PopupProperties = TooltipPopupProperties,

    content: @Composable ColumnScope.() -> Unit,
) {
    val expandedStates = remember { MutableTransitionState(false) }
    expandedStates.targetState = expanded.value

    if (expandedStates.currentState || expandedStates.targetState) {
        if (expandedStates.isIdle) {
            LaunchedEffect(timeoutMillis, expanded) {
                delay(timeoutMillis)
                expanded.value = false
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 40.dp, vertical = 20.dp)
        ) {
            TooltipContent(expandedStates, backgroundColor, modifier, content)
        }
    }
}
@Composable
private fun TooltipContent(
    expandedStates: MutableTransitionState<Boolean>,
    backgroundColor: Color,
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val transition = updateTransition(expandedStates, "Tooltip")
    val alpha by transition.animateFloat(
        label = "alpha",
        transitionSpec = {
            if (false isTransitioningTo true) {
                tween(durationMillis = InTransitionDuration)
            } else {
                tween(durationMillis = OutTransitionDuration)
            }
        }
    ) { if (it) 1f else 0f }
    Card(
        backgroundColor = backgroundColor.copy(alpha = 0.75f),
        contentColor = MaterialTheme.colors.contentColorFor(backgroundColor)
            .takeOrElse { Color.Black },
        modifier = Modifier.alpha(alpha),
        ) {
        val p = TooltipPadding
        Column(
            modifier = modifier
                .padding(start = p, top = p * 0.5f, end = p, bottom = p * 0.7f)
                .width(IntrinsicSize.Max)
                .verticalScroll(rememberScrollState()),
            content = content,
        )
    }
}