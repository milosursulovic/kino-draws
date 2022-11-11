package com.example.mozzartkino.domain.use_case

import com.example.mozzartkino.data.model.Draw
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.domain.repository.KinoRepository

class GetDrawById(
    private val repository: KinoRepository
) {
    suspend fun execute(drawId: String): Resource<Draw> {
        return repository.getDrawsById(drawId)
    }
}