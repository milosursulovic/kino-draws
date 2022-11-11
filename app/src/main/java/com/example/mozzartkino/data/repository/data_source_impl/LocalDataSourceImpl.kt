package com.example.mozzartkino.data.repository.data_source_impl

import androidx.lifecycle.LiveData
import com.example.mozzartkino.data.db.KinoDao
import com.example.mozzartkino.data.repository.data_source.LocalDataSource
import com.example.mozzartkino.domain.model.Draw

class LocalDataSourceImpl(
    private val dao: KinoDao
) : LocalDataSource {
    override suspend fun saveDraw(draw: Draw) {
        dao.saveDraw(draw)
    }

    override fun getDraws(): LiveData<List<Draw>> {
        return dao.getAllDraws()
    }
}