package com.example.mozzartkino.di

import android.app.Application
import com.example.mozzartkino.presentation.adapters.KinoAdapter
import com.example.mozzartkino.presentation.adapters.NumbersAdapter
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
    fun providesKinoAdapter(app: Application): KinoAdapter {
        return KinoAdapter(app)
    }

    @Provides
    @Singleton
    fun providesNumbersAdapter(): NumbersAdapter {
        return NumbersAdapter()
    }
}