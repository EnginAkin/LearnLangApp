package com.eng.learnlang.feature_test.presentation.teach_detail

import com.eng.learnlang.core.domain.model.Word

data class TeachState(
    val currentIndex : Int = 0,
    val wordList: List<Word> ?=null,
    val isLoading:Boolean =false,

)
