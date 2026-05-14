package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.entity.DestinationEntity
import com.cmc.mytravelcompany.domain.repository.DestinationRepository
import javax.inject.Inject

class GetDestinationsUseCase @Inject constructor(
    private val repository: DestinationRepository
) {
    suspend operator fun invoke(): List<DestinationEntity> {
        return repository.getDestinations()
    }

    suspend operator fun invoke(id: String): DestinationEntity? {
        return repository.getDestinationById(id)
    }
}
