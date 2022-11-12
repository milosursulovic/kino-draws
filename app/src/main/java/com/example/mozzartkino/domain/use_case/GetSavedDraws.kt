package com.example.mozzartkino.domain.use_case

import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.domain.repository.KinoRepository
import kotlinx.coroutines.flow.Flow

class GetSavedDraws(
    private val repository: KinoRepository
) {
    suspend fun execute(): Flow<List<Draw>> {
        return repository.getSavedDraws()
    }
}