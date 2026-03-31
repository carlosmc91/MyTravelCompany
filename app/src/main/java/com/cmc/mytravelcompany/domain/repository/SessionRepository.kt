package com.cmc.mytravelcompany.domain.repository

import com.cmc.mytravelcompany.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun getSession(): Flow<UserEntity?>
    suspend fun saveSession(user: UserEntity)
    suspend fun clearSession()
}