package com.eng.learnlang.presentation.topic_word_details_content

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.eng.learnlang.domain.model.Category
import com.eng.learnlang.domain.model.Sentence
import com.eng.learnlang.domain.model.Word
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