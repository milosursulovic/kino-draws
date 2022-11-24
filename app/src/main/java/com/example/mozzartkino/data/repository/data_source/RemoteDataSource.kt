package com.example.mozzartkino.data.repository.data_source

import com.example.mozzartkino.data.model.draw.DrawDto
import com.example.mozzartkino.data.model.results.ResultsDto

interface RemoteDataSource {
    suspend fun getDraws(): List<DrawDto>
    suspend fun getResults(fromDate: String, toDate: String): ResultsDto
}