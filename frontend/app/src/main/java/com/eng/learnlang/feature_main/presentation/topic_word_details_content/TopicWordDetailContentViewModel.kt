package com.eng.learnlang.feature_main.presentation.topic_word_details_content

import androidx.compose.material.icons.materialIcon
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopicWordDetailContentViewModel @Inject constructor(
     savedStateHandle: SavedStateHandle,
   val mainFeedUseCases: MainFeedUseCases
) : ViewModel() {



    init {
        var categoryName:String=""
        var day:Int=0
        var limit:Int=0
        savedStateHandle.get<String>("categoryName")?.let {
            categoryName=it
        }
        savedStateHandle.get<Int>("day")?.let {
            day=it
        }
        savedStateHandle.get<Int>("limit")?.let {
            limit=it
        }
        loadWordsByCategoryNameWithPagination(categoryName,day,limit)

    }
    fun onEvent(event: WordContentEvent){


        when(event){
            is WordContentEvent.AddListWordClick->{

            }
            is WordContentEvent.verifiedWord ->{

            }
        }
    }

    private fun loadWordsByCategoryNameWithPagination(categoryName: String, day: Int, limit: Int) {
        viewModelScope.launch {
        val result = mainFeedUseCases.getWordsWithPaginationByCategoryName(categoryName,day, limit)
            when(result){
                is Resource.Error -> {
                    println("resource failed  : result message ${result.message}")

                }
                is Resource.Success -> {
                    println("resource succes : result data size ${result.data?.size}")
                    println("resource succes : result data ${result.data}")
                }
            }
        }

    }




}