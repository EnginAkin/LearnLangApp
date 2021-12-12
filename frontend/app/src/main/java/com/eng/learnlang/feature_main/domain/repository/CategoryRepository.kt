package com.eng.learnlang.feature_main.domain.repository

import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.TopicWordDay
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource

interface CategoryRepository {
    suspend fun getCategoriesPost():Resource<List<Category>>
    suspend fun getWordsWithPaginationByCategoryName(categoryName : String,pageNumber : Int, limit : Int): Resource<List<Word>>
    suspend fun getWordsByCategoryName(categoryName : String): Resource<List<Word>>
    suspend fun getUsersLearnedWords(userId : Long): Resource<List<Word>>
}