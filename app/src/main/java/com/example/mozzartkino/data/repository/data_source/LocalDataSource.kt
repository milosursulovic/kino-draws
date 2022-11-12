package com.example.mozzartkino.data.repository.data_source

import com.example.mozzartkino.domain.model.Draw
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveDraw(draw: Draw)
    fun getDraws(): Flow<List<Draw>>
}