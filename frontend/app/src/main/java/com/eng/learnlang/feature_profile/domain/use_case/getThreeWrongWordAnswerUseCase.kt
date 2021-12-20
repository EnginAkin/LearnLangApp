package com.eng.learnlang.feature_profile.domain.use_case

import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.repository.MainWordRepository
import com.eng.learnlang.feature_profile.domain.repository.TestRepository

class getThreeWrongWordAnswerUseCase (
    private val repository: TestRepository
    ){
    suspend operator fun invoke(id : Long):Resource<List<Word>>{
        return repository.getThreeWrongWordAnswer(id)
    }
}