package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.repository.SessionRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.clearSession()
}