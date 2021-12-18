package com.eng.learnlang.feature_main.domain.use_case

data class MainFeedUseCases (
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getWordsWithPaginationByCategoryName: GetWordsWithPaginationByCategoryName,
    val getWordsByCategoryName: GetWordsByCategoryName,
    val getUserLearnedWords:GetUserLearnedWords,
    val addLearnedWordListInUserUseCase:AddLearnedWordListInUserUseCase,
    val addUserWordListUseCase:AddUserWordListUseCase,
    val getUserWordListUseCase:GetUserWordListUseCase,
    val deleteUserWordListByWordIdUseCase:DeleteUserWordListByWordIdUseCase,
    val getWordsByWordListUseCase: GetWordsByWordListUseCase
)
