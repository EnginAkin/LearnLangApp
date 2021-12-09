package com.eng.learnlang.feature_main.data.remote

import com.eng.learnlang.core.data.dto.response.GeneralApiResponse
import com.eng.learnlang.core.domain.model.Category
import retrofit2.http.GET

interface WordsApi {
    @GET("/api/v1/getCategoriesWithInfo")
    suspend fun getCategoriesWithInfo():List<Category>

    companion object{
        const val BASE_URL="http://192.168.1.8:8082"

    }
}