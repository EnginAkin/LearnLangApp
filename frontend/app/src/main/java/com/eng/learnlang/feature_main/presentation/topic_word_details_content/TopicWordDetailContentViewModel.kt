package com.eng.learnlang.feature_main.present

import com.eng.learnlang.feature_main.presentation.topic_word_details_content.WordContentEvent
import com.eng.learnlang.feature_main.presentation.topic_word_details_content.WordDetailContentState


import android.content.SharedPreferences
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import com.eng.learnlang.feature_main.presentation.TopicWordDetailsScreen.WordDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopicWordDetailContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val mainFeedUseCases: MainFeedUseCases,
    val sharedPreferences: SharedPreferences

) : ViewModel() {

    private val _state = mutableStateOf(WordDetailContentState())
    val state: State<WordDetailContentState> = _state

    private val listLearnedWords = mutableListOf<Word>();
    var categoryName: String = ""
    var day: Int = 0
    var limit: Int = 0
    init {
        val userId = sharedPreferences.getLong(Constants.KEY_USER_ID, 0)
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
            is WordContentEvent.AddListWordClick -> {
            // TODO Öğrenme Biliyorum tarzında bir buton eklenecek ve ona gore öğrenmeden geçebilecektir.
            }
            is WordContentEvent.VerifiedWord -> {

            }
            is WordContentEvent.StartLearning ->{

            }
        }
    }

    private suspend fun loadUserLearnedWords(userId: Long) {
        viewModelScope.launch {
            val result = mainFeedUseCases.getUserLearnedWords(userId)
            _state.value=_state.value.copy(
                isLoading = true
            )
            when (result) {
                is Resource.Success -> {
                    println("loadUserLearnedWords is succes ${result.data}")
                    result.data?.forEachIndexed { index, word ->
                        listLearnedWords.add(word)
                    }
                    loadWordsByCategoryNameWithPagination(categoryName, day, limit)
                    _state.value=_state.value.copy(
                        isLoading = false
                    )

                }
                is Resource.Error -> {
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
                if(listLearnedWords.contains(word)){
                    println("içeriyor = ${word.name}")
                    _state.value.wordList!!.find { it.name==word.name }!!.verified=true
                }
            }
        }else{
            println("gelen değer boş")

        }
    }


}