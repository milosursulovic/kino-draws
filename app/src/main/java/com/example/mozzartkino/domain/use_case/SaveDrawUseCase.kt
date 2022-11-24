package com.example.mozzartkino.domain.use_case

import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.domain.repository.KinoRepository

class SaveDrawUseCase(
    private val repository: KinoRepository
) {
    suspend operator fun invoke(draw: Draw) {
        repository.saveDraw(draw)
    }
}