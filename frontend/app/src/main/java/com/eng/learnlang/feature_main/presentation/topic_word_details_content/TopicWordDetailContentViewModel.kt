package com.eng.learnlang.feature_main.present

import android.app.Application
import com.eng.learnlang.feature_main.presentation.topic_word_details_content.WordContentEvent
import com.eng.learnlang.feature_main.presentation.topic_word_details_content.WordDetailContentState


import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import com.eng.learnlang.util.speak
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopicWordDetailContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val mainFeedUseCases: MainFeedUseCases,
    val sharedPreferences: SharedPreferences,
    val application: Application
) : ViewModel() {

    private val _state = mutableStateOf(WordDetailContentState())
    val state: State<WordDetailContentState> = _state

    private val listLearnedWords = mutableListOf<Word>();
    private val applicationContext = application.applicationContext

    private val _sharedFlow= MutableSharedFlow<UiEvent>()
    val sharedFlow=_sharedFlow.asSharedFlow()
    var categoryName: String = ""
    var day: Int = 0
    var limit: Int = 0
    var userId: Long = 0

    init {
        userId = sharedPreferences.getLong(Constants.KEY_USER_ID, 0)
        viewModelScope.launch {
            loadUserLearnedWords(userId)
        }

        savedStateHandle.get<String>("categoryName")?.let {
            categoryName = it
        }
        savedStateHandle.get<Int>("day")?.let {
            day = it
        }
        savedStateHandle.get<Int>("limit")?.let {
            limit = it
        }


    }




    fun onEvent(event: WordContentEvent) {
        when (event) {
            is WordContentEvent.AddMyListWordClicked -> {
                viewModelScope.launch {
                    val result = mainFeedUseCases.addUserWordListUseCase(userId,event.wordId)
                    when(result){
                        is Resource.Success ->{
                            _sharedFlow.emit(UiEvent.SnackbarEvent("Kelime Listesine Eklendi"))

                        }
                        is Resource.Error ->{
                            _sharedFlow.emit(UiEvent.SnackbarEvent("Kelime Listesine Eklenemedi !!!!"))

                        }
                    }
                }
            }
            is WordContentEvent.AddLearnedListWordClick -> {
                viewModelScope.launch {
                    var result =
                        mainFeedUseCases.addLearnedWordListInUserUseCase(userId, event.wordId)
                    when (result) {
                        is Resource.Success -> {
                            println("gelen id = ${event.wordId} ")
                            _state.value.wordList!!.find { it.id?.toLong() == event.wordId }!!.verified = true
                            println("değişim  ${ _state.value.wordList!!.find { it.id?.toLong() == event.wordId }} ")

                            _sharedFlow.emit(UiEvent.SnackbarEvent("Kelime Başarıyla Eklendi"))
                        }
                        is Resource.Error -> {
                            _sharedFlow.emit(UiEvent.SnackbarEvent("Kelime Eklenemedi. Daha sonra tekrar deneyiniz"+result.message))
                        }
                    }
                }
            }
            is WordContentEvent.StartLearning -> {
                // TODO navigate to teach deatils _sharedFlow.emit(UiEvent.Navigate(Screen.TeachDetailScreen.route+))
                viewModelScope.launch {
                    var unLearnedWordsId=getUnLearnedWords()
                    _sharedFlow.emit(UiEvent.Navigate(Screen.TeachDetailScreen.route+"?wordsId=${unLearnedWordsId}"))
                }
            }
            is WordContentEvent.CLickListenWord -> {
                speak(text = event.word, applicationContext)
            }
        }
    }

    private fun getUnLearnedWords(): String{
        var words= _state.value.wordList?.filter { it.isWordInMyWordList==false }
        var wordsId = ArrayList<Long>()
        if (words != null) {
            words.forEachIndexed { index, word ->
                if(!word.verified){
                        word.id?.let { wordsId.add(it.toLong()) }
                }
            }
        }
        return  wordsId.toString()
    }

    private suspend fun loadUserLearnedWords(userId: Long) {
        viewModelScope.launch {
            val result = mainFeedUseCases.getUserLearnedWords(userId)
            _state.value = _state.value.copy(
                isLoading = true
            )
            when (result) {
                is Resource.Success -> {
                    result.data?.forEachIndexed { index, word ->
                        listLearnedWords.add(word)
                    }
                    loadWordsByCategoryNameWithPagination(categoryName, day, limit)
                    _state.value = _state.value.copy(
                        isLoading = false
                    )

                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                    loadWordsByCategoryNameWithPagination(categoryName, day, limit)
                    println("loadUserLearnedWords is error ${result.message}")
                }
                null -> {
                    println("loadUserLearnedWords is null")

                }
            }
        }
    }

    private fun loadWordsByCategoryNameWithPagination(categoryName: String, day: Int, limit: Int) {
        viewModelScope.launch {
            val result =
                mainFeedUseCases.getWordsWithPaginationByCategoryName(categoryName, day, limit)
            _state.value = _state.value.copy(
                isLoading = true
            )
            when (result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = true, isLoading = false
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        error = false,
                        wordList = result.data,
                        isLoading = false
                    )
                    verifiedWordsMatch()
                }
            }
        }
    }

    private fun verifiedWordsMatch() {

        if (_state.value.wordList != null) {
            println("word list state : ${_state.value.wordList}")
            println("word list learned user : ${listLearnedWords}")
            _state.value.wordList!!.forEachIndexed { index, word ->
                if (listLearnedWords.contains(word)) {
                    println("içeriyor = ${word.name}")
                    _state.value.wordList!!.find { it.name == word.name }!!.verified = true
                }
            }
        }
    }


}