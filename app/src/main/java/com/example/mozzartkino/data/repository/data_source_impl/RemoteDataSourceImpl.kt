package com.example.mozzartkino.data.repository.data_source_impl

import com.example.mozzartkino.data.api.KinoApiService
import com.example.mozzartkino.data.model.draw.DrawDto
import com.example.mozzartkino.data.model.results.ResultsDto
import com.example.mozzartkino.data.repository.data_source.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(
    private val api: KinoApiService
) : RemoteDataSource {
    override suspend fun getDraws(): Response<List<DrawDto>> {
        return api.getDraws()
    }

    override suspend fun getResults(fromDate: String, toDate: String): Response<ResultsDto> {
        return api.getResults(fromDate, toDate)
    }
}