package com.example.mozzartkino.di

import android.app.Application
import com.example.mozzartkino.domain.use_case.GetDrawById
import com.example.mozzartkino.domain.use_case.GetDraws
import com.example.mozzartkino.presentation.view_models.KinoViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FactoryModule {
    @Provides
    @Singleton
    fun providesKinoViewModelFactory(
        app: Application,
        getDraws: GetDraws,
        getDrawById: GetDrawById
    ): KinoViewModelFactory {
        return KinoViewModelFactory(
            app,
            getDraws,
            getDrawById
        )
    }
}