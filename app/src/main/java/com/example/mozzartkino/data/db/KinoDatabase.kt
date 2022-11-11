package com.example.mozzartkino.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mozzartkino.domain.model.Draw

@Database(entities = [Draw::class], version = 1, exportSchema = false)
abstract class KinoDatabase : RoomDatabase() {
    abstract val kinoDao: KinoDao
}