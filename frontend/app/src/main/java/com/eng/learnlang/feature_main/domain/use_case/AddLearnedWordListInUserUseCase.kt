package com.eng.learnlang.feature_main.domain.use_case

import com.eng.learnlang.core.domain.model.TopicWordDay
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_main.domain.repository.CategoryRepository

class AddLearnedWordListInUserUseCase (
    private val repository: CategoryRepository
){
    suspend operator fun invoke(userId : Long,wordId : Long): SimpleResource {
      return repository.addUserLearnedWordList(wordId,userId)
    }
}