package com.eng.learnlang.feature_test.presentation.test

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.data.repository.WordsWrapper
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import com.eng.learnlang.feature_profile.domain.use_case.TestUseCases
import com.eng.learnlang.feature_test.presentation.teach_detail.TeachState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Error
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class TestViewModel @Inject constructor(
    val mainFeedUseCases: MainFeedUseCases,
    val testUseCases: TestUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(TestState())
    val state: State<TestState> = _state

    init {
        savedStateHandle.get<String>("wordsId")?.let {


            var listIds=getTestWordSplitWithId(it)
            println("işlemden sonra : ${listIds} ")
            getWordsWithIds(listIds)
        }
    }



    private fun getWordsWithIds(listId: List<String>){
        var wordsWrapper = WordsWrapper()
        wordsWrapper.wordIds=listId
        viewModelScope.launch {
            var result = mainFeedUseCases.getWordsByWordListUseCase(wordsWrapper)
            _state.value=_state.value.copy(
                isLoading = true
            )
            when(result){
                is Resource.Success ->{
                    _state.value=_state.value.copy(
                        isLoading = false,
                        wordList = result.data as ArrayList<Word>
                    )
                }
                is Resource.Error ->{
                    println("Test detail view model failed  :")
                    _state.value=_state.value.copy(
                        isLoading = false,
                        error = "Yüklenirken bir hata oluştu"
                    )
                }
            }

        }
    }
    fun setWrongWordList(word: Word ): List<Word>{
        if(_state.value.wordList!!.size>=4){
            var listofWrong=_state.value.wordList!!.filter { it.id!!.toLong() != word.id!!.toLong() } as ArrayList<Word>
                listofWrong.shuffle()
                listofWrong=listofWrong.take(3) as ArrayList<Word>
                listofWrong.add(word)
            return listofWrong
        }else{
            println("word list size <4 list sized : ${_state.value.wordList!!.size}")
                getThreeWrongAnswerForQuestion(word.id!!.toLong())

            return listOf()
        }

    }

    private fun getThreeWrongAnswerForQuestion(id: Long) {
        viewModelScope.launch {
            var result =testUseCases.getThreeWrongWordAnswerUseCase(id)
            when(result){
                is Resource.Success ->{
                    println("3 ynlış cevp elime ulaştı " +result.data!!.size)
                    // TODO yapılacaklar
                    
                }
                is Resource.Error ->{
                    println("3 ynlış cevp failed  " +result.message)

                }
            }
        }
    }

    private fun getTestWordSplitWithId(stringId: String):List<String> {
        var newString =stringId.replace("\\s".toRegex(),"")
        return newString.dropLast(1).drop(1).split(",")
    }
}
