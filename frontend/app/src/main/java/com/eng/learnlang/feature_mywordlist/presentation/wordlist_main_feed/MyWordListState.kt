package com.eng.learnlang.feature_mywordlist.presentation.wordlist_main_feed

import com.eng.learnlang.core.domain.model.Word

data class MyWordListState (
    val wordList: List<Word> ?=null,
    val isloading : Boolean =false,
    val error : String =""
)