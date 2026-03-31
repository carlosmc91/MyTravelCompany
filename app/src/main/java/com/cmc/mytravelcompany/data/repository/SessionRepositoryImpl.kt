package com.cmc.mytravelcompany.data.repository

import com.cmc.mytravelcompany.data.datasource.datastore.SessionManager
import com.cmc.mytravelcompany.domain.entity.UserEntity
import com.cmc.mytravelcompany.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionManager: SessionManager
) : SessionRepository {
    override fun getSession(): Flow<UserEntity?> = sessionManager.userData
    override suspend fun saveSession(user: UserEntity) = sessionManager.saveUser(user)
    override suspend fun clearSession() = sessionManager.logout()
}