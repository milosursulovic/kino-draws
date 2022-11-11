package com.example.mozzartkino.data.repository.data_source

import androidx.lifecycle.LiveData
import com.example.mozzartkino.data.model.Draw

interface LocalDataSource {
    suspend fun saveDraw(draw: Draw)
    fun getDraws(): LiveData<List<Draw>>
}