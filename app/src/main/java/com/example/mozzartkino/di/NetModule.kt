package com.example.mozzartkino.di

import com.example.mozzartkino.data.api.KinoApiService
import com.example.mozzartkino.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.API_URL)
        .build()

    @Provides
    @Singleton
    fun providesKinoApiService(retrofit: Retrofit): KinoApiService =
        retrofit.create(KinoApiService::class.java)
}