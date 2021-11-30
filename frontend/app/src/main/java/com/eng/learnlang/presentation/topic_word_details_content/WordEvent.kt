package com.eng.learnlang.presentation.topic_word_details_content

import com.eng.learnlang.domain.model.Word

sealed class WordContentEvent {
    data class verifiedWord(val value: Boolean) : WordContentEvent()
    data class AddListWordClick(val value: Word) : WordContentEvent()

}