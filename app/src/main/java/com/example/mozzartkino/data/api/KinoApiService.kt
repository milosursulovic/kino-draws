package com.example.mozzartkino.data.api

import com.example.mozzartkino.data.model.draw.DrawDto
import com.example.mozzartkino.data.model.results.ResultsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface KinoApiService {
    @GET("/draws/v3.0/1100/upcoming/20")
    suspend fun getDraws(): List<DrawDto>

    @GET("/draws/v3.0/1100/draw-date/{fromDate}/{toDate}")
    suspend fun getResults(
        @Path("fromDate") fromDate: String,
        @Path("toDate") toDate: String
    ): ResultsDto
}