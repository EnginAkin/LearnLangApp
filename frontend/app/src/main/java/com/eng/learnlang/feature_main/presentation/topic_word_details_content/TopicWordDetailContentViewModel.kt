package com.eng.learnlang.feature_main.presentation.topic_word_details_content

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TopicWordDetailContentViewModel @Inject constructor() : ViewModel() {




    fun onEvent(event: WordContentEvent){


        when(event){
            is WordContentEvent.AddListWordClick->{

            }
            is WordContentEvent.verifiedWord ->{

            }
        }
    }


}