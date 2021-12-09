package com.eng.learnlang.feature_library.presentation.library_details

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.eng.learnlang.core.domain.state.StandartTextFiedlState
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.feature_library.domain.TranslatorBackgroundTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LibraryDetailViewModel @Inject constructor(application: Application) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    val context=application.applicationContext


    private val _state = mutableStateOf(BookState())
    val state: State<BookState> = _state

    fun onEvent(event: BookEvent){


    }

    private fun Translate(textToBeTranslated: String, languagePair: String) {
        val translatorBackgroundTask = TranslatorBackgroundTask(context)
        val result=translatorBackgroundTask.execute(textToBeTranslated,languagePair)

        val textToBeTranslated = "take"
        val languagePair = "en-tr"
        Translate(textToBeTranslated, languagePair)

    }



}