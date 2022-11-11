package com.example.mozzartkino.domain.use_case

import com.example.mozzartkino.data.model.DrawDto
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.domain.repository.KinoRepository

class GetDrawById(
    private val repository: KinoRepository
) {
    suspend fun execute(drawId: String): Resource<DrawDto> {
        return repository.getDrawsById(drawId)
    }
}