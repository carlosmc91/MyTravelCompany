package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.entity.BenefitEntity
import com.cmc.mytravelcompany.domain.repository.BenefitRepository
import javax.inject.Inject

class GetBenefitsUseCase @Inject constructor(
    private val repository: BenefitRepository
) {
    suspend operator fun invoke(): List<BenefitEntity> {
        return repository.getBenefits()
    }
}
