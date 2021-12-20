package com.eng.learnlang.feature_profile.data.remote

import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.feature_main.data.repository.WordsWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApi {
    @GET("${VERSION_URL}word/getThreeWrongWordAnswer")
    suspend fun getThreeWrongWordAnswer(@Query("id") id: Long) : List<Word>

    companion object{
        const val BASE_URL= Constants.WORDS_API_BASE_URL
        const val VERSION_URL="/api/v1/"
    }
}