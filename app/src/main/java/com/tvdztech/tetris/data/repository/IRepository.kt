package com.tvdztech.tetris.data.repository

import com.tvdztech.tetris.data.model.IPLocation
import com.tvdztech.tetris.data.model.RemoteConfigs
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getDetailIPLocation(): IPLocation
    fun initConfigs()

    fun getConfigs(): Flow<RemoteConfigs>
}