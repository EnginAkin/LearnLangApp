package com.eng.learnlang.feature_auth.data.remote

import com.eng.learnlang.core.data.dto.response.GeneralApiResponse
import com.eng.learnlang.feature_auth.data.remote.request.CreateAccountRequest
import com.eng.learnlang.feature_auth.data.remote.request.Credential
import com.eng.learnlang.feature_auth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST
import java.lang.Exception

interface AuthApi {

    @POST("api/v1/auth/signup")
    suspend fun signup(@Body createAccountRequest: CreateAccountRequest) : GeneralApiResponse<Unit>

 /*
    @POST("api/v1/login")
    suspend fun login(@Body credential: Credential) : GeneralApiResponse
  */
    @POST("api/v1/auth/login")
    suspend fun login(
     @Body request: Credential
    ):GeneralApiResponse<AuthResponse>


    companion object {

        const val BASE_URL="http://192.168.1.5:8082"
    }




}