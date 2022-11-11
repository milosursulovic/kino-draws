package com.example.mozzartkino.data.api

import com.example.mozzartkino.data.model.DrawDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KinoApiService {
    @GET("/draws/v3.0/1100/upcoming/20")
    suspend fun getDraws(): Response<List<DrawDto>>

    @GET("/draws/v3.0/1100/{drawId}")
    suspend fun getDrawById(@Query("drawId") drawId: String): Response<DrawDto>
}