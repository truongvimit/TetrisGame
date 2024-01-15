package com.truongvim.snakegame.domain.repository

import com.tvdztech.tetris.data.model.IPLocation
import com.tvdztech.tetris.data.repository.IPLocationApi
import com.tvdztech.tetris.data.repository.IRepository
import com.tvdztech.tetris.data.repository.RemoteConfigRepo

class RepositoryImpl(
    private val remoteConfigRepo: RemoteConfigRepo,
    private val ipLocationApi: IPLocationApi,
): IRepository {
    override suspend fun getDetailIPLocation(): IPLocation = ipLocationApi.getDetailIPLocation()
    override fun initConfigs() = remoteConfigRepo.initConfigs()

    override fun getConfigs() = remoteConfigRepo.getConfigs()
}