package com.example.mozzartkino.domain.repository

import com.example.mozzartkino.data.model.draw.DrawDto
import com.example.mozzartkino.data.model.results.ResultsDto
import com.example.mozzartkino.domain.model.Draw
import kotlinx.coroutines.flow.Flow

interface KinoRepository {
    suspend fun getDraws(): List<DrawDto>
    suspend fun getResults(fromDate: String, toDate: String): ResultsDto
    suspend fun saveDraw(draw: Draw)
    fun getSavedDraws(): Flow<List<Draw>>
}