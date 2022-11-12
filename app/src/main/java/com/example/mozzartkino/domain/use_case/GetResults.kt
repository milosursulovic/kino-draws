package com.example.mozzartkino.domain.use_case

import com.example.mozzartkino.data.model.results.ResultsDto
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.domain.repository.KinoRepository

class GetResults(
    private val repository: KinoRepository
) {
    suspend fun execute(fromDate: String, toDate: String): Resource<ResultsDto> {
        return repository.getResults(fromDate, toDate)
    }
}