package com.example.mozzartkino.di

import com.example.mozzartkino.domain.repository.KinoRepository
import com.example.mozzartkino.domain.use_case.GetDraws
import com.example.mozzartkino.domain.use_case.GetSavedDraws
import com.example.mozzartkino.domain.use_case.SaveDraw
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
    fun providesGetDraws(repository: KinoRepository): GetDraws {
        return GetDraws(repository)
    }

    @Provides
    @Singleton
    fun providesSaveDraw(repository: KinoRepository): SaveDraw {
        return SaveDraw(repository)
    }

    @Provides
    @Singleton
    fun providesGetSavedDraws(repository: KinoRepository): GetSavedDraws {
        return GetSavedDraws(repository)
    }
}