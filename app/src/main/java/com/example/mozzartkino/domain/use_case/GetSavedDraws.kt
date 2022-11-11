package com.example.mozzartkino.domain.use_case

import androidx.lifecycle.LiveData
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.domain.repository.KinoRepository

class GetSavedDraws(
    private val repository: KinoRepository
) {
    suspend fun execute(): LiveData<List<Draw>> {
        return repository.getSavedDraws()
    }
}