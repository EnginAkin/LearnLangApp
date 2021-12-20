package com.eng.learnlang.di

import com.eng.learnlang.feature_main.data.remote.WordsApi
import com.eng.learnlang.feature_main.data.repository.MainWordRepositoryImpl
import com.eng.learnlang.feature_main.domain.repository.MainWordRepository
import com.eng.learnlang.feature_main.domain.use_case.*
import com.eng.learnlang.feature_profile.data.remote.TestApi
import com.eng.learnlang.feature_profile.data.repository.TestRepositoryImpl
import com.eng.learnlang.feature_profile.domain.repository.TestRepository
import com.eng.learnlang.feature_profile.domain.use_case.TestUseCases
import com.eng.learnlang.feature_profile.domain.use_case.getThreeWrongWordAnswerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestModule {
    @Provides
    @Singleton
    fun provideTestApi(client: OkHttpClient): TestApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WordsApi.BASE_URL)
            .client(client)
            .build()
            .create(TestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTestRepository(api: TestApi): TestRepository {
        return TestRepositoryImpl(api)
    }
    @Singleton
    @Provides
    fun provideTestFeedUseCases(repository: TestRepository): TestUseCases {
       return TestUseCases(
           getThreeWrongWordAnswerUseCase(repository)
       )
    }
}