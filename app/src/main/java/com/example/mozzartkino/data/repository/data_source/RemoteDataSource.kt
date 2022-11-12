package com.example.mozzartkino.data.repository.data_source

import com.example.mozzartkino.data.model.draw.DrawDto
import com.example.mozzartkino.data.model.results.ResultsDto
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getDraws(): Response<List<DrawDto>>
    suspend fun getResults(fromDate: String, toDate: String): Response<ResultsDto>
}