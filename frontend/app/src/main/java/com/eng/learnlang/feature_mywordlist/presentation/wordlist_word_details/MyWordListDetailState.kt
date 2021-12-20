package com.eng.learnlang.feature_mywordlist.presentation.wordlist_word_details

import com.eng.learnlang.core.domain.model.TopicWordDay
import com.eng.learnlang.core.domain.model.Word

data class MyWordListDetailState(
    val text: String = "",
    val testedWords: ArrayList<Long> = ArrayList(),
    val wordList: List<Word> ?=null,
    val isLoading: Boolean =false,
    val error :String=""
)
