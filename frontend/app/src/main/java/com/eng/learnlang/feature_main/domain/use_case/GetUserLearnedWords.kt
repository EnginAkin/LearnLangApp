package com.eng.learnlang.feature_main.domain.use_case

import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.repository.CategoryRepository

class GetUserLearnedWords (
    private val repository: CategoryRepository
    ){
    suspend operator fun invoke(userId : Long):Resource<List<Word>>{
        return repository.getUsersLearnedWords(userId);
    }
}