package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.entity.BannerEntity
import com.cmc.mytravelcompany.domain.repository.BannerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBannersUseCase @Inject constructor(
    private val repository: BannerRepository
) {
    operator fun invoke(): Flow<List<BannerEntity>> {
        return repository.getBanners()
    }
}
