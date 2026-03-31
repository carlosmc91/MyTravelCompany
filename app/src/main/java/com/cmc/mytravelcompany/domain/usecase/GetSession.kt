package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.repository.SessionRepository
import javax.inject.Inject

class GetSession @Inject constructor(private val repository: SessionRepository) {
    operator fun invoke() = repository.getSession()
}