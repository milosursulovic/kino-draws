package com.example.mozzartkino.di

import com.example.mozzartkino.data.repository.KinoRepositoryImpl
import com.example.mozzartkino.data.repository.data_source.RemoteDataSource
import com.example.mozzartkino.domain.repository.KinoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesKinoRepository(remoteDataSource: RemoteDataSource): KinoRepository {
        return KinoRepositoryImpl(remoteDataSource)
    }
}