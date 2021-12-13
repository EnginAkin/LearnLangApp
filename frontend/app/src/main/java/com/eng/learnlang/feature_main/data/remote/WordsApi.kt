package com.eng.learnlang.feature_main.data.remote

import com.eng.learnlang.core.data.dto.response.GeneralApiResponse
import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Constants.WORDS_API_BASE_URL
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface WordsApi {
    @GET("/api/v1/getCategoriesWithInfo")
    suspend fun getCategoriesWithInfo():List<Category>
    @GET("/api/v1/getWordsByCategoryNameAndWithPage/{categoryName}")
   suspend fun getWordsByCategoryNameAndpageble(@Path("categoryName") categoryName: String,@Query("pageNumber") pageNumber : Int,@Query("limit") limit : Int):List<Word>

    @GET("/api/v1/getWordsWithCategoryName")
    suspend fun getWordsByCategoryName(@Query("categoryName") categoryName : String):List<Word>

    @GET("/api/v1/user/learnedWords")
    suspend fun getUsersLearnedWords(@Query("userId") userId :Long):List<Word>

    @POST("/api/v1/word/addLearnedList/{wordId}/{userId}")
    suspend fun addUserLearnedWordList(@Path("wordId") wordId :Long, @Path("userId") userId :Long)

    companion object{
        const val BASE_URL=WORDS_API_BASE_URL
    }
}