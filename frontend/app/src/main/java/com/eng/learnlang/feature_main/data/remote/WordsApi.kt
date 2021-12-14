package com.eng.learnlang.feature_main.data.remote

import com.eng.learnlang.core.data.dto.response.GeneralApiResponse
import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Constants.WORDS_API_BASE_URL
import retrofit2.http.*

interface WordsApi {

    @GET("${VERSION_URL}getCategoriesWithInfo")
    suspend fun getCategoriesWithInfo():List<Category>

    @GET("${VERSION_URL}getWordsByCategoryNameAndWithPage/{categoryName}")
   suspend fun getWordsByCategoryNameAndpageble(@Path("categoryName") categoryName: String,@Query("pageNumber") pageNumber : Int,@Query("limit") limit : Int):List<Word>

    @GET("${VERSION_URL}getWordsWithCategoryName")
    suspend fun getWordsByCategoryName(@Query("categoryName") categoryName : String):List<Word>

    @GET("${VERSION_URL}user/learnedWords")
    suspend fun getUsersLearnedWords(@Query("userId") userId :Long):List<Word>

    @GET("${VERSION_URL}user/wordList/{userId}")
    suspend fun getUserWordList(@Path("userId") userId :Long):List<Word>


    @POST("${VERSION_URL}word/addLearnedList/")
    suspend fun addUserLearnedWordList(@Query("wordId") wordId :Long, @Query("userId") userId :Long)

    @DELETE("${VERSION_URL}word/deleteUserWordList/")
    suspend fun deleteWordInUserWordList(@Query("wordId") wordId :Long, @Query("userId") userId :Long)

    @POST("${VERSION_URL}word/addUserWordList")
    suspend fun addUserWordList(@Query("wordId") wordId :Long, @Query("userId") userId :Long)

    companion object{
        const val BASE_URL=WORDS_API_BASE_URL
        const val VERSION_URL="/api/v1/"
    }
}