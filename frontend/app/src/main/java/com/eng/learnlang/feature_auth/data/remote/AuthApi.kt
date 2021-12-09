package com.eng.learnlang.feature_auth.data.remote

import com.eng.learnlang.core.data.dto.response.GeneralApiResponse
import com.eng.learnlang.feature_auth.data.remote.request.CreateAccountRequest
import com.eng.learnlang.feature_auth.data.remote.request.Credential
import com.eng.learnlang.feature_auth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.lang.Exception

interface AuthApi {

    @POST("api/v1/auth/signup")
    suspend fun signup(@Body createAccountRequest: CreateAccountRequest) : GeneralApiResponse

 /*
    @POST("api/v1/login")
    suspend fun login(@Body credential: Credential) : GeneralApiResponse
  */
    @POST("api/v1/auth/login")
    suspend fun login(
     @Body request: Credential
    ):GeneralApiResponse

    @GET("api/v1/authanticate")
    suspend fun authenticate():GeneralApiResponse

    companion object {

        const val BASE_URL="http://192.168.1.8:8082"
    }




}