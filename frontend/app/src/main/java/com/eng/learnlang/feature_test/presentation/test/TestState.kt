package com.eng.learnlang.feature_test.presentation.test

import com.eng.learnlang.core.domain.model.Word

data class TestState(
    val currentIndex : Int = 0,
    val wordList: List<Word> ?=null,
    val isLoading:Boolean =false,
)
