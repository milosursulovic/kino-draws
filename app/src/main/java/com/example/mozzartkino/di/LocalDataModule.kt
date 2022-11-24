package com.example.mozzartkino.di

import com.example.mozzartkino.data.db.KinoDao
import com.example.mozzartkino.data.repository.data_source.LocalDataSource
import com.example.mozzartkino.data.repository.data_source_impl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun providesLocalDataSource(dao: KinoDao): LocalDataSource = LocalDataSourceImpl(dao)
}