package com.eng.learnlang.feature_auth.data.remote

import com.eng.learnlang.core.data.dto.response.GeneralApiResponse
import com.eng.learnlang.feature_auth.data.remote.request.CreateAccountRequest
import com.eng.learnlang.feature_auth.data.remote.request.Credential
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/v1/signup")
    suspend fun signup(@Body createAccountRequest: CreateAccountRequest) : GeneralApiResponse

 /*
    @POST("api/v1/login")
    suspend fun login(@Body credential: Credential) : GeneralApiResponse
  */

    companion object {
        const val BASE_URL="http://192.168.1.6:8082"
    }

}