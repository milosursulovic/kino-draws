package com.example.mozzartkino.domain.use_case

import android.app.Application
import com.example.mozzartkino.R
import com.example.mozzartkino.data.model.draw.toDraw
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.domain.repository.KinoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetDrawsUseCase(
    private val app: Application,
    private val repository: KinoRepository
) {
    operator fun invoke(): Flow<Resource<List<Draw>>> = flow {
        emit(Resource.Loading())
        try {
            val draws = repository.getDraws().map { it.toDraw() }
            emit(Resource.Success(draws))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: app.resources.getString(R.string.error_occurred)
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(app.resources.getString(R.string.internet_not_available)))
        }
    }
}