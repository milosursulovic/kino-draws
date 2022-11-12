package com.example.mozzartkino.di

import android.app.Application
import com.example.mozzartkino.domain.use_case.GetDraws
import com.example.mozzartkino.domain.use_case.GetResults
import com.example.mozzartkino.domain.use_case.GetSavedDraws
import com.example.mozzartkino.domain.use_case.SaveDraw
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
        getResults: GetResults,
        saveDraw: SaveDraw,
        getSavedDraws: GetSavedDraws
    ): KinoViewModelFactory {
        return KinoViewModelFactory(
            app,
            getDraws,
            getResults,
            saveDraw,
            getSavedDraws
        )
    }
}