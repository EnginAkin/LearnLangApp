package com.eng.learnlang.feature_main.domain.use_case

import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_main.domain.repository.MainWordRepository

class DeleteUserWordListByWordIdUseCase (
    private val repository: MainWordRepository
){
    suspend operator fun invoke(userId : Long,wordId : Long): SimpleResource {
      return repository.deleteUserWordListByWordId(wordId,userId)
    }
}