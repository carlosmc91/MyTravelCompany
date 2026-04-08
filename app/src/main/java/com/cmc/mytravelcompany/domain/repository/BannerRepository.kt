package com.cmc.mytravelcompany.domain.repository

import com.cmc.mytravelcompany.domain.entity.BannerEntity

interface BannerRepository {
    suspend fun getBanners(): Result<List<BannerEntity>>
}
