package com.eng.learnlang.feature_main.domain.use_case

import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.repository.MainWordRepository

class GetCategoriesUseCase (
    private val repository: MainWordRepository
    ){
    suspend operator fun invoke():Resource<List<Category>>{
        return repository.getCategoriesPost();
    }
}