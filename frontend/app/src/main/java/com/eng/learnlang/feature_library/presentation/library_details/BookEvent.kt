package com.eng.learnlang.feature_library.presentation.library_details

sealed class BookEvent{
    data class clickedWord(val value : String): BookEvent()
}
