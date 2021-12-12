package com.eng.learnlang.feature_main.presentation.TopicWordDetailsScreen

import com.eng.learnlang.core.domain.model.TopicWordDay

data class WordDetailState(
    val topicList: List<TopicWordDay> ?=null,
    val isLoading: Boolean =false
)