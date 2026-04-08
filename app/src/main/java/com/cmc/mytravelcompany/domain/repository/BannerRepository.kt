package com.cmc.mytravelcompany.domain.repository

import com.cmc.mytravelcompany.domain.entity.BannerEntity
import kotlinx.coroutines.flow.Flow

interface BannerRepository {
    fun getBanners(): Flow<List<BannerEntity>>
}
