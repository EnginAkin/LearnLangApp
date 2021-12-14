package com.eng.learnlang.feature_main.presentation.topic_word_details_content

import com.eng.learnlang.core.domain.model.Word

sealed class WordContentEvent {
    data class AddMyListWordClicked(val wordId: Long) : WordContentEvent()
    data class AddLearnedListWordClick(val wordId: Long) : WordContentEvent()
    data class CLickListenWord(val word: String) : WordContentEvent()
    object StartLearning : WordContentEvent()

}