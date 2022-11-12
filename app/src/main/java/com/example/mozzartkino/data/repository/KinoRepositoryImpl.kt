package com.example.mozzartkino.data.repository

import com.example.mozzartkino.data.model.DrawDto
import com.example.mozzartkino.data.repository.data_source.LocalDataSource
import com.example.mozzartkino.data.repository.data_source.RemoteDataSource
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.domain.repository.KinoRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class KinoRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : KinoRepository {
    override suspend fun getDraws(): Resource<List<DrawDto>> {
        return responseToResource(remoteDataSource.getDraws())
    }

    override suspend fun getDrawsById(drawId: String): Resource<DrawDto> {
        return responseToResource(remoteDataSource.getDrawById(drawId))
    }

    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        response.run {
            if (isSuccessful) {
                body()?.let { result ->
                    return Resource.Success(result)
                } ?: return Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveDraw(draw: Draw) {
        localDataSource.saveDraw(draw)
    }

    override suspend fun getSavedDraws(): Flow<List<Draw>> {
        return localDataSource.getDraws()
    }
}