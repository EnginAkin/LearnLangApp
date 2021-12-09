package com.eng.learnlang.feature_main.presentation.topic_word

import androidx.lifecycle.ViewModel
import com.eng.learnlang.feature_main.domain.use_case.GetCategoriesUseCase
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicWordsViewModel @Inject constructor(
private val mainFeedUseCases: MainFeedUseCases
): ViewModel() {



}