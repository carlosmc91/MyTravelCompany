package com.cmc.mytravelcompany.domain.repository

import com.cmc.mytravelcompany.domain.entity.BenefitEntity

interface BenefitRepository {
    suspend fun getBenefits(): List<BenefitEntity>
}