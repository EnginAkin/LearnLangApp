package com.eng.learnlang.feature_auth.data.repository

import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_auth.data.remote.AuthApi
import com.eng.learnlang.feature_auth.data.remote.request.CreateAccountRequest
import com.eng.learnlang.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource {

        return try {
            val request= CreateAccountRequest(email, username, password)
            val response = api.signup(request)
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { message ->
                    Resource.Error(message)
                } ?: Resource.Error("Bilinmeyen hata")
            }
        } catch (e: IOException) {
            Resource.Error(
                message = "Birşeyler ters gitti ! Servere ulaşılamıyor"
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = "please try again e :" + e.localizedMessage
            )
        }

    }
}