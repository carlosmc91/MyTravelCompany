package com.cmc.mytravelcompany.domain.usecase

import com.cmc.mytravelcompany.domain.entity.UserEntity
import com.cmc.mytravelcompany.domain.repository.AuthRepository
import javax.inject.Inject

class SignIn @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: String, password: String): UserEntity? {
        if (user.contains("@hotmail.com")) {
            return null
        } else {
            val response = authRepository.signUp(user, password, null)
            return response.getOrNull()
        }
    }
}