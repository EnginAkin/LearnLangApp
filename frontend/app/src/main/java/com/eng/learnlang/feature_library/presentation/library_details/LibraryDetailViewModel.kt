package com.eng.learnlang.feature_library.presentation.library_details

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.eng.learnlang.core.domain.state.StandartTextFiedlState
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.feature_library.domain.TranslatorBackgroundTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.os.AsyncTask

import android.os.Build
import android.util.Log





@SuppressLint("StaticFieldLeak")
@HiltViewModel
class LibraryDetailViewModel @Inject constructor(application: Application) : ViewModel() {

    val context=application.applicationContext


    private val _state = mutableStateOf(BookState())
    val state: State<BookState> = _state
    val languagePair = "en-tr"

    fun onEvent(event: BookEvent){
        when(event){
            is BookEvent.clickedWord ->{
               val mean  =Translate(event.value,languagePair)
                _state.value=_state.value.copy(
                    meanForToolTip = mean?:"HATA"
                )
            }

        }

    }

    fun Translate(textToBeTranslated: String, languagePair: String) :String?{
        val translatorBackgroundTask = TranslatorBackgroundTask(context)
        val result:String=translatorBackgroundTask.execute(textToBeTranslated,languagePair).get()
        println("çeviri işlem sonucu : $result.}")
        return result?:"Hata!!"

    }



}