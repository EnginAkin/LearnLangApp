package com.eng.learnlang.feature_main.presentation.TopicWordDetailsScreen

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.domain.model.TopicWordDay
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Constants.KEY_USER_ID
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class TopicWordDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val mainFeedUseCases: MainFeedUseCases,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = mutableStateOf(WordDetailState())
    val state: State<WordDetailState> = _state

    private val listLearnedWords = mutableListOf<Word>();


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        val userId = sharedPreferences.getLong(KEY_USER_ID, 0)
        loadUserLearnedWords(userId)
        println("user id ? $userId")
        savedStateHandle.get<String>("categoryName")?.let {
            loadTopicWordsDay(it)
        }
    }

    private fun loadTopicWordsDay(categoryName: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            val result = mainFeedUseCases.getWordsByCategoryName(categoryName)
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                       _state.value=_state.value.copy(
                           topicList =extractWordsByTopicWordDay(it)
                       )
                    }
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                    println("result succes : ${_state.value.topicList}")
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.SnackbarEvent("Yüklenirken Sorun Yaşandı"))
                }
                null -> {

                }
            }
        }
    }

    private fun loadUserLearnedWords(userId: Long) {
        viewModelScope.launch {
            val result = mainFeedUseCases.getUserLearnedWords(userId)
            when (result) {
                is Resource.Success -> {
                    result.data?.forEachIndexed { index, word ->
                        listLearnedWords.add(word)
                    }
                    println("load learned words : ${result.data}")

                }
                is Resource.Error -> {
                    println(result.message)
                }
                null -> {
                    println("null geldi")
                }
            }
        }
    }

    private fun extractWordsByTopicWordDay(data: List<Word>, totalWordCount: Int = 2) :List<TopicWordDay>{
        val size = ceil(data.size.toDouble() / totalWordCount).toInt()

        val listofTopicWord= mutableListOf<TopicWordDay>()
        for (i in 0..size - 1) {
            // 0 -> 0-4 ---- 1 -> 5-10
            var lastIndex = (i * totalWordCount) + totalWordCount
            if (lastIndex > data.size) {
                lastIndex = data.size
            }
            val learnedCount = learnedCount(data.subList(i * totalWordCount, lastIndex))
            val topicWordDay = data[i].category?.let {
                TopicWordDay(
                    it.categoryName,
                    i,
                    totalWordCount,
                    learnedCount
                )
            }
            if (topicWordDay != null) {
                listofTopicWord.add(topicWordDay)
            }
        }
        return listofTopicWord

    }

    private fun learnedCount(wordList: List<Word>): Int {
        var totalLearnedCount = 0
        if (listLearnedWords.size > 0) {
            wordList.forEachIndexed { index, word ->
                if (listLearnedWords.contains(word)) {
                    totalLearnedCount++;
                }
            }
        }
        return totalLearnedCount
    }


}