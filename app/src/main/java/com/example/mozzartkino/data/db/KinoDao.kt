package com.example.mozzartkino.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mozzartkino.domain.model.Draw
import kotlinx.coroutines.flow.Flow

@Dao
interface KinoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDraw(draw: Draw)

    @Query("select * from draws")
    fun getAllDraws(): Flow<List<Draw>>
}