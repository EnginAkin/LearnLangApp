package com.eng.learnlang.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.provider.SyncStateContract
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.core.util.Constants.SHARED_PREFERENCE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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

    @Provides
    @Singleton
    fun provideJwtToken(sharedPreferences: SharedPreferences): String{
        return sharedPreferences.getString(Constants.KEY_JWT_TOKEN,"") ?:"";
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(jwtToken : String) : OkHttpClient {
        println("token is : $jwtToken")
        return OkHttpClient.Builder()
            .addInterceptor {
                   val modifiedRequest= it.request().newBuilder().addHeader("Authorization",jwtToken)
                .build()
                it.proceed(modifiedRequest)

            }
            .build()
    }
}