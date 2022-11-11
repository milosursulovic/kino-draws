package com.example.mozzartkino.di

import com.example.mozzartkino.presentation.adapters.KinoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {
    @Provides
    @Singleton
    fun providesKinoAdapter(): KinoAdapter {
        return KinoAdapter()
    }
}