package com.tvdztech.tetris.domain.interactors

import com.tvdztech.tetris.data.model.IPLocation
import com.tvdztech.tetris.data.repository.IRepository
import com.tvdztech.tetris.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetIPLocationDetailUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Unit, IPLocation>(dispatcher) {
    override suspend fun block(param: Unit): IPLocation = repository.getDetailIPLocation()
}