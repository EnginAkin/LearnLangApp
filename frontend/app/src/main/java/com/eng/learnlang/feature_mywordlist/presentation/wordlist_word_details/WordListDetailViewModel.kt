package com.eng.learnlang.feature_mywordlist.presentation.wordlist_word_details

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordListDetailViewModel @Inject constructor(
    val application: Application,
    val mainFeedUseCases: MainFeedUseCases,
    private val sharedPreferences: SharedPreferences
) :ViewModel(){

    private val _state = mutableStateOf(MyWordListDetailState())
    val state: State<MyWordListDetailState> = _state

    private var listAllWords  : List<Word> = emptyList()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow=_eventFlow.asSharedFlow()

    init {
        val userId = sharedPreferences.getLong(Constants.KEY_USER_ID, 0)
        getMyWordList(userId)

    }

    private fun getMyWordList(userId: Long) {
        viewModelScope.launch {
            val result =mainFeedUseCases.getUserWordListUseCase(userId)
            _state.value=_state.value.copy(
                isLoading = true
            )
            when(result){
                is Resource.Success ->{
                    println("İşlem başarılı . ${result.data}")
                    _state.value=_state.value.copy(
                        isLoading = false,
                        wordList = result.data
                    )
                    listAllWords= result.data!!

                }
                is Resource.Error ->{
                    _state.value=_state.value.copy(
                        isLoading = false,
                        error = "Yüklenirken bir hata oluştu."
                    )
                }
            }
        }
    }

    fun onEvent(event: MyWordListDetailEvent) {
       when(event){
           is MyWordListDetailEvent.clickedWordContentForTested ->{
               val list = _state.value.testedWords
               if(!list.contains(event.id))
               list.add(event.id)
               else{
                   list.remove(event.id)
               }
               _state.value=_state.value.copy(
                   testedWords = list
               )
           }
           is MyWordListDetailEvent.clickedNextTest ->{
               viewModelScope.launch {
                   _eventFlow.emit(UiEvent.Navigate(Screen.TestDetailScreen.route+"?wordsId=${_state.value.testedWords.toString()}"))
               }
           }
           is MyWordListDetailEvent.enteredTextForSearch ->{
               _state.value=_state.value.copy(
                   text = event.text
               )
               var list : ArrayList<Word>  = ArrayList()
               listAllWords.forEachIndexed { index, word ->

                   if(word.name!!.toLowerCase().contains(event.text.toLowerCase())){
                       list.add(word)
                   }
               }
               _state.value=_state.value.copy(
                    wordList = list
               )

           }
       }
    }

    fun iselected(id: Long): Boolean {
        _state.value.testedWords.forEachIndexed { index, wordId ->
            if(wordId==id) return true
        }
        return false
    }


    sealed class MyWordListDetailEvent(){
        data class clickedWordContentForTested(val id: Long) : MyWordListDetailEvent()
        object clickedNextTest:MyWordListDetailEvent()
        data class enteredTextForSearch(val text :String) : MyWordListDetailEvent()
    }

}