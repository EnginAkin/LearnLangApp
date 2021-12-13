package com.eng.learnlang.feature_main.domain.use_case

import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.repository.CategoryRepository

class GetWordsWithPaginationByCategoryName (
    private val repository: CategoryRepository
){
    suspend operator fun invoke(categoryName : String,pageNumber : Int,limit: Int): Resource<List<Word>> {
        return repository.getWordsWithPaginationByCategoryName(categoryName,pageNumber,limit)
    }
}