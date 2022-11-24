package com.example.mozzartkino.di

import android.app.Application
import androidx.room.Room
import com.example.mozzartkino.data.db.KinoDao
import com.example.mozzartkino.data.db.KinoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(app: Application): KinoDatabase = Room.databaseBuilder(
        app,
        KinoDatabase::class.java,
        "kino_db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesKinoDao(db: KinoDatabase): KinoDao = db.kinoDao
}