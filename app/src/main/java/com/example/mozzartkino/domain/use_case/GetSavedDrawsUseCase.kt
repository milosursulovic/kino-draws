package com.example.mozzartkino.domain.use_case

import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.domain.repository.KinoRepository
import kotlinx.coroutines.flow.Flow

class GetSavedDrawsUseCase(
    private val repository: KinoRepository
) {
    operator fun invoke(): Flow<List<Draw>> = repository.getSavedDraws()
}