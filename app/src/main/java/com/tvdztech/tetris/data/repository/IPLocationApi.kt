package com.tvdztech.tetris.data.repository

import com.tvdztech.tetris.data.model.IPLocation
import retrofit2.http.GET

interface IPLocationApi {
    @GET("json")
    suspend fun getDetailIPLocation(): IPLocation
}