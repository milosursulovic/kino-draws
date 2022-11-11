package com.example.mozzartkino.di

import com.example.mozzartkino.domain.repository.KinoRepository
import com.example.mozzartkino.domain.use_case.GetDraws
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
}