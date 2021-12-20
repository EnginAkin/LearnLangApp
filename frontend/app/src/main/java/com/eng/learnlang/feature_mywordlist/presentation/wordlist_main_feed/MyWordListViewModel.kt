package com.eng.learnlang.feature_mywordlist.presentation.wordlist_main_feed

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import com.eng.learnlang.util.speak
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyWordListViewModel @Inject constructor(
    val application: Application,
    val mainFeedUseCases: MainFeedUseCases,
    private val sharedPreferences: SharedPreferences
) :ViewModel(){

    private val _state = mutableStateOf(MyWordListState())
    val state: State<MyWordListState> = _state

    init {
        val userId = sharedPreferences.getLong(Constants.KEY_USER_ID, 0)
        getMyWordList(userId)

    }

    private fun getMyWordList(userId: Long) {
        viewModelScope.launch {
            val result =mainFeedUseCases.getUserWordListUseCase(userId)
            _state.value=_state.value.copy(
                isloading = true
            )
            when(result){
                is Resource.Success ->{
                    println("İşlem başarılı . ${result.data}")
                    _state.value=_state.value.copy(
                        isloading = false,
                        wordList = result.data
                    )

                }
                is Resource.Error ->{
                    _state.value=_state.value.copy(
                        isloading = false,
                        error = "Yüklenirken bir hata oluştu."
                    )
                }
            }
        }
    }

    fun onEvent(event: MyWordListEvent){
            when(event){
                is MyWordListEvent.ClickStartTestButton ->{

                }
                is MyWordListEvent.ListenWord ->{
                    speak(event.word,application)
                }
            }
    }

    sealed class MyWordListEvent(){
        object ClickStartTestButton:MyWordListEvent()
        data class ListenWord(val word : String):MyWordListEvent()
    }

}