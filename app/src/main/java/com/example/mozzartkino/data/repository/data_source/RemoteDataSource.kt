package com.example.mozzartkino.data.repository.data_source

import com.example.mozzartkino.data.model.Draw
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getDraws(): Response<List<Draw>>
    suspend fun getDrawById(drawId: String): Response<Draw>
}