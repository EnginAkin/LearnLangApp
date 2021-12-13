package com.eng.learnlang.feature_main.presentation.topic_word_details_content

import com.eng.learnlang.core.domain.model.TopicWordDay
import com.eng.learnlang.core.domain.model.Word

data class WordDetailContentState(
    val wordList: List<Word> ?=null,
    val isLoading: Boolean =false,
    val error: Boolean = false,
)