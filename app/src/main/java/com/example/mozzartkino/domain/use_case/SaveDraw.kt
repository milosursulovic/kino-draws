package com.example.mozzartkino.domain.use_case

import com.example.mozzartkino.data.model.Draw
import com.example.mozzartkino.domain.repository.KinoRepository

class SaveDraw(
    private val repository: KinoRepository
) {
    suspend fun execute(draw: Draw) {
        repository.saveDraw(draw)
    }
}