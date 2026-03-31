package com.cmc.mytravelcompany.domain.repository

import com.cmc.mytravelcompany.domain.entity.UserEntity

interface AuthRepository {
    suspend fun doLogin(user: String, password: String): List<UserEntity>
}