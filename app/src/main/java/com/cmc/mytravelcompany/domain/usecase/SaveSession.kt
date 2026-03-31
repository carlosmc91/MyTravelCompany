package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.entity.UserEntity
import com.cmc.mytravelcompany.domain.repository.SessionRepository
import javax.inject.Inject

class SaveSession @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke(user: UserEntity) = repository.saveSession(user)
}