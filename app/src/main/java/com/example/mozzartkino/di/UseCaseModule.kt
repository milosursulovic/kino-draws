package com.example.mozzartkino.di

import android.app.Application
import com.example.mozzartkino.domain.repository.KinoRepository
import com.example.mozzartkino.domain.use_case.GetDrawsUseCase
import com.example.mozzartkino.domain.use_case.GetResultsUseCase
import com.example.mozzartkino.domain.use_case.GetSavedDrawsUseCase
import com.example.mozzartkino.domain.use_case.SaveDrawUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesGetDraws(app: Application, repository: KinoRepository): GetDrawsUseCase =
        GetDrawsUseCase(app, repository)

    @Provides
    @Singleton
    fun providesGetResults(app: Application, repository: KinoRepository): GetResultsUseCase =
        GetResultsUseCase(app, repository)

    @Provides
    @Singleton
    fun providesSaveDraw(repository: KinoRepository): SaveDrawUseCase = SaveDrawUseCase(repository)

    @Provides
    @Singleton
    fun providesGetSavedDraws(repository: KinoRepository): GetSavedDrawsUseCase =
        GetSavedDrawsUseCase(repository)
}