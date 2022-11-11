package com.example.mozzartkino.domain.repository

import androidx.lifecycle.LiveData
import com.example.mozzartkino.data.model.DrawDto
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.domain.model.Draw

interface KinoRepository {
    suspend fun getDraws(): Resource<List<DrawDto>>
    suspend fun getDrawsById(drawId: String): Resource<DrawDto>
    suspend fun saveDraw(draw: Draw)
    suspend fun getSavedDraws(): LiveData<List<Draw>>
}