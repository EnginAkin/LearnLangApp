package com.eng.learnlang.di

import com.eng.learnlang.feature_main.data.remote.WordsApi
import com.eng.learnlang.feature_main.data.repository.MainWordRepositoryImpl
import com.eng.learnlang.feature_main.domain.repository.MainWordRepository
import com.eng.learnlang.feature_main.domain.use_case.*
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
object WordsModule {

    @Provides
    @Singleton
    fun provideWordsApi(client: OkHttpClient):WordsApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WordsApi.BASE_URL)
            .client(client)
            .build()
            .create(WordsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(api: WordsApi):MainWordRepository{
        return MainWordRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideMainFeedUseCases(repository: MainWordRepository):MainFeedUseCases{
        return MainFeedUseCases(
            getCategoriesUseCase = GetCategoriesUseCase(repository),
            getWordsWithPaginationByCategoryName = GetWordsWithPaginationByCategoryName(repository),
            getWordsByCategoryName = GetWordsByCategoryName(repository),
            getUserLearnedWords = GetUserLearnedWords(repository),
            addLearnedWordListInUserUseCase= AddLearnedWordListInUserUseCase(repository),
            addUserWordListUseCase = AddUserWordListUseCase(repository),
            getUserWordListUseCase=GetUserWordListUseCase(repository),
            deleteUserWordListByWordIdUseCase=DeleteUserWordListByWordIdUseCase(repository )
        )
    }
}