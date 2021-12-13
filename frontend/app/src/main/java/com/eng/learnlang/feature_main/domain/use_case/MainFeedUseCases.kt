package com.eng.learnlang.feature_main.domain.use_case

data class MainFeedUseCases (
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getWordsWithPaginationByCategoryName: GetWordsWithPaginationByCategoryName,
    val getWordsByCategoryName: GetWordsByCategoryName,
    val getUserLearnedWords:GetUserLearnedWords
)
