package com.example.mozzartkino.data.repository

import com.example.mozzartkino.data.model.Draw
import com.example.mozzartkino.data.repository.data_source.RemoteDataSource
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.domain.repository.KinoRepository
import retrofit2.Response

class KinoRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): KinoRepository {
    override suspend fun getDraws(): Resource<List<Draw>> {
        return responseToResource(remoteDataSource.getDraws())
    }

    override suspend fun getDrawsById(drawId: String): Resource<Draw> {
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
}