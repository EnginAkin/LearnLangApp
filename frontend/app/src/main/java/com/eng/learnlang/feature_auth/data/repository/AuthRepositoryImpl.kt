package com.eng.learnlang.feature_auth.data.repository

import android.content.SharedPreferences
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.core.util.Constants.KEY_JWT_TOKEN
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_auth.data.remote.AuthApi
import com.eng.learnlang.feature_auth.data.remote.request.CreateAccountRequest
import com.eng.learnlang.feature_auth.data.remote.request.Credential
import com.eng.learnlang.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
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

    override suspend fun login(email: String, password: String): SimpleResource {
        return try {
            val request= Credential(email, password);
            val response = api.login(request)

            if (response.successful) {
              if(response.message!=null){ // tokeni mesajın içinde gönderiyorum
                  response.userInfo?.id?.let {
                      sharedPreferences.edit()
                          .putString(KEY_JWT_TOKEN,response.message)
                          .putLong(Constants.KEY_USER_ID, it)
                          .apply()
                  }
              }

                Resource.Success(Unit)
            } else {
                response.message?.let { message ->
                    Resource.Error(message)
                } ?: Resource.Error("Kullanıcı adı veya şifre hatalı")
            }
        } catch (e: IOException) {
            Resource.Error(
                message = "Birşeyler ters gitti ! Servere ulaşılamıyor"
            )
        } catch (e: HttpException) {
            println("exception :  ${e.localizedMessage}")
            Resource.Error(
                message = "please try again e :" + e.localizedMessage
            )
        }
    }

    override suspend fun authenticate(): SimpleResource {

        return try {
            api.authenticate()
            Resource.Success(Unit)
        }catch (e : IOException){
            println("giriş başarısız 1")
            Resource.Error(
                message = "Birşeyler ters gitti ! Servere ulaşılamıyor"
            )
        }catch (e: HttpException){
            println("giriş başarısız 2")
            Resource.Error(
                message = "please try again e :" + e.localizedMessage
            )
        }
    }
}