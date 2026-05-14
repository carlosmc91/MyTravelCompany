package com.cmc.mytravelcompany.domain.repository

import com.cmc.mytravelcompany.domain.entity.DestinationEntity

interface DestinationRepository {
    suspend fun getDestinations(): List<DestinationEntity>
    suspend fun getDestinationById(id: String): DestinationEntity?
}
