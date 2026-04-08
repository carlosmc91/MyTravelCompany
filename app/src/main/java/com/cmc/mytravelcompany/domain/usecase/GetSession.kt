package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.repository.AuthRepository
import javax.inject.Inject

class GetSession @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.currentUser
}
