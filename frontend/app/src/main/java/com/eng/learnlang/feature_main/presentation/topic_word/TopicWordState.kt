package com.eng.learnlang.feature_main.presentation.topic_word

import com.eng.learnlang.core.domain.model.Category
import java.util.*

data class TopicWordState(
    val categories: List<Category> = emptyList(),
    val isLoading : Boolean =false
)
