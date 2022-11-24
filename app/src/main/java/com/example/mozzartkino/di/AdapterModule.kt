package com.example.mozzartkino.di

import android.app.Application
import com.example.mozzartkino.presentation.adapters.KinoAdapter
import com.example.mozzartkino.presentation.adapters.NumbersAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {
    @Provides
    @FragmentScoped
    fun providesKinoAdapter(app: Application): KinoAdapter = KinoAdapter(app)

    @Provides
    @FragmentScoped
    fun providesNumbersAdapter(): NumbersAdapter = NumbersAdapter()
}