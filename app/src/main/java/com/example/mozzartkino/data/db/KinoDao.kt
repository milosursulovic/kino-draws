package com.example.mozzartkino.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mozzartkino.domain.model.Draw

@Dao
interface KinoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDraw(draw: Draw)

    @Query("select * from draws")
    fun getAllDraws(): LiveData<List<Draw>>
}