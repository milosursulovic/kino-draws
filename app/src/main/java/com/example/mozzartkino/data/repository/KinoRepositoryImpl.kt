package com.example.mozzartkino.data.repository

import com.example.mozzartkino.data.model.draw.DrawDto
import com.example.mozzartkino.data.model.results.ResultsDto
import com.example.mozzartkino.data.repository.data_source.LocalDataSource
import com.example.mozzartkino.data.repository.data_source.RemoteDataSource
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.domain.repository.KinoRepository
import kotlinx.coroutines.flow.Flow

class KinoRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : KinoRepository {
    override suspend fun getDraws(): List<DrawDto> {
        return remoteDataSource.getDraws()
    }

    override suspend fun getResults(fromDate: String, toDate: String): ResultsDto {
        return remoteDataSource.getResults(fromDate, toDate)
    }

    override suspend fun saveDraw(draw: Draw) {
        localDataSource.saveDraw(draw)
    }

    override fun getSavedDraws(): Flow<List<Draw>> {
        return localDataSource.getDraws()
    }
}