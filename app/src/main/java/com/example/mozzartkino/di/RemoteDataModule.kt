package com.example.mozzartkino.di

import com.example.mozzartkino.data.api.KinoApiService
import com.example.mozzartkino.data.repository.data_source.RemoteDataSource
import com.example.mozzartkino.data.repository.data_source_impl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    @Singleton
    fun providesRemoteDataSource(api: KinoApiService): RemoteDataSource {
        return RemoteDataSourceImpl(api)
    }
}