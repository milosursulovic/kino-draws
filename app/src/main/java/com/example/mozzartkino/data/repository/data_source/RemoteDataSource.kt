package com.example.mozzartkino.data.repository.data_source

import com.example.mozzartkino.data.model.DrawDto
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getDraws(): Response<List<DrawDto>>
}