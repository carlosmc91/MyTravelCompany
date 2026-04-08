package com.cmc.mytravelcompany.domain.repository

import com.cmc.mytravelcompany.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    // Acciones de autenticación
    suspend fun login(email: String, password: String): Result<UserEntity>
    suspend fun signUp(email: String, password: String, name: String?): Result<UserEntity>
    suspend fun logout()

    // Estado de la sesión: Emite el usuario cuando cambia el estado de auth
    val currentUser: Flow<UserEntity?>
}
