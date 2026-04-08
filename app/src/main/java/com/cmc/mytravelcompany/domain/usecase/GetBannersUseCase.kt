package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.entity.BannerEntity
import com.cmc.mytravelcompany.domain.repository.BannerRepository
import javax.inject.Inject

class GetBannersUseCase @Inject constructor(
    private val repository: BannerRepository
) {
    suspend operator fun invoke(): Result<List<BannerEntity>> {
        return repository.getBanners()
    }
}
