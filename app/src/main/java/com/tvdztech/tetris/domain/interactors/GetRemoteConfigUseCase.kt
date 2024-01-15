package com.tvdztech.tetris.domain.interactors

import com.tvdztech.tetris.data.model.RemoteConfigs
import com.tvdztech.tetris.data.repository.IRepository
import domain.interactors.type.BaseUseCaseFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetRemoteConfigUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseFlow<Unit, RemoteConfigs>(dispatcher) {
    override suspend fun build(param: Unit): Flow<RemoteConfigs> = repository.getConfigs()
}