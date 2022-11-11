package com.example.mozzartkino.domain.repository

import com.example.mozzartkino.data.model.Draw
import com.example.mozzartkino.data.util.Resource

interface KinoRepository {
    suspend fun getDraws(): Resource<List<Draw>>
    suspend fun getDrawsById(drawId: String): Resource<Draw>
}