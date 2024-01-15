package com.tvdztech.tetris.data.repository

import com.tvdztech.tetris.data.model.RemoteConfigs
import kotlinx.coroutines.flow.Flow


/**
 * @Author Mbuodile Obiosio
 * Twitter: @cazewonder
 * Remote Configuration Repository interface
 */
interface RemoteConfigRepo {

    fun initConfigs()

    fun getConfigs(): Flow<RemoteConfigs>
}