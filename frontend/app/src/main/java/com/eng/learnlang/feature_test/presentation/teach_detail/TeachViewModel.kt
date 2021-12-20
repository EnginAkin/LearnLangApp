package com.eng.learnlang.feature_test.presentation.teach_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.feature_main.data.repository.WordsWrapper
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeachViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val mainFeedUseCases: MainFeedUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TeachState())
    val state: State<TeachState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow=_eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("wordsId")?.let {
            println("gelindi")

            var listIds=getTeachWordSplitWithId(it)
            getWordsWithIds(listIds)
        }
    }

    fun onEvent(event: TeachEvent) {
        when(event){
            is TeachEvent.LoadNext ->{
                _state.value=_state.value.copy(
                    currentIndex = _state.value.currentIndex+1
                )
            }
            is TeachEvent.LoadTest ->{
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.Navigate(Screen.TestDetailScreen.route))
                }
            }
        }
    }

    private fun getWordsWithIds(listId: List<String>){
        var wordsWrapper = WordsWrapper()
        wordsWrapper.wordIds=listId
        viewModelScope.launch {
           var result = mainFeedUseCases.getWordsByWordListUseCase(wordsWrapper)
            _state.value= _state.value.copy(
               isLoading = true
            )
            when(result){
                is Resource.Success ->{
                    println("Test detail view model succes : ${result.data}")
                    _state.value= _state.value.copy(
                        wordList = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error ->{
                    _state.value= _state.value.copy(
                        isLoading = false
                    )
                }
            }

        }
    }

    private fun getTeachWordSplitWithId(stringId: String):List<String> {
        var newString =stringId.replace("\\s".toRegex(),"")
         return newString.dropLast(1).drop(1).split(",")
    }



    sealed class TeachEvent {
            object LoadNext:TeachEvent()
            object LoadTest:TeachEvent()
    }

}
