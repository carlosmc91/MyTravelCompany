package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.logout()
}
