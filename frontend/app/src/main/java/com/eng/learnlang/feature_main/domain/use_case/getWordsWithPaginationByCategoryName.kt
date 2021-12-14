package com.eng.learnlang.feature_main.domain.use_case

import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.repository.MainWordRepository

class GetWordsWithPaginationByCategoryName (
    private val repository: MainWordRepository
){
    suspend operator fun invoke(categoryName : String,pageNumber : Int,limit: Int): Resource<List<Word>> {
        return repository.getWordsWithPaginationByCategoryName(categoryName,pageNumber,limit)
    }
}