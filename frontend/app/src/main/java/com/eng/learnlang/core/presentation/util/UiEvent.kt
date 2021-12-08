package com.eng.learnlang.core.presentation.util

sealed class UiEvent {

        data class  SnackbarEvent(val message : String): UiEvent()
        data class  Navigate(val route : String): UiEvent()

}