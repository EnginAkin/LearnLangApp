package com.eng.learnlang.di

import android.content.SharedPreferences
import com.eng.learnlang.feature_auth.data.remote.AuthApi
import com.eng.learnlang.feature_auth.data.remote.AuthApi.Companion.BASE_URL
import com.eng.learnlang.feature_auth.data.repository.AuthRepositoryImpl
import com.eng.learnlang.feature_auth.domain.repository.AuthRepository
import com.eng.learnlang.feature_auth.domain.use_case.LoginUseCase
import com.eng.learnlang.feature_auth.domain.use_case.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {




    @Provides
    @Singleton
    fun provideAuthApi():AuthApi{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }



    @Provides
    @Singleton
    fun provideAuthRepository(api : AuthApi,sharedPreferences: SharedPreferences) :AuthRepository{
        return AuthRepositoryImpl(api,sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository : AuthRepository) : RegisterUseCase{
        return RegisterUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repository : AuthRepository) : LoginUseCase{
        return LoginUseCase(repository)
    }


}