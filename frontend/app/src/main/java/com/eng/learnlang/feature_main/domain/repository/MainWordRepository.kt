package com.eng.learnlang.feature_main.domain.repository

import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.TopicWordDay
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_main.data.repository.WordsWrapper
import retrofit2.http.Path
import retrofit2.http.Query

interface MainWordRepository {
    suspend fun getCategoriesPost():Resource<List<Category>>
    suspend fun getWordsWithPaginationByCategoryName(categoryName : String,pageNumber : Int, limit : Int): Resource<List<Word>>
    suspend fun getWordsByCategoryName(categoryName : String): Resource<List<Word>>
    suspend fun getUsersLearnedWords(userId : Long): Resource<List<Word>>
    suspend fun addUserLearnedWordList( wordId :Long,userId :Long): SimpleResource
    suspend fun addUserWordList( wordId :Long,  userId :Long):SimpleResource
    suspend fun getUserWordList(userId :Long):Resource<List<Word>>
    suspend fun deleteUserWordListByWordId(wordId :Long,userId: Long):SimpleResource
    suspend fun getWordsByWordList(wrapper: WordsWrapper): Resource<List<Word>>


}