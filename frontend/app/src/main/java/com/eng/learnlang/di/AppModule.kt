package com.eng.learnlang.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.eng.learnlang.core.util.Constants.SHARED_PREFERENCE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // bağımlılıkları bu kısma yazacağız
    @Provides
    @Singleton
    fun provideSharedPreference(app : Application) : SharedPreferences {
        return app.getSharedPreferences(SHARED_PREFERENCE_NAME,
        MODE_PRIVATE)
    }
}