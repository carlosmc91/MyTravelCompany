package com.cmc.mytravelcompany.data.repository

import com.cmc.mytravelcompany.data.datasource.api.ApiService
import com.cmc.mytravelcompany.data.datasource.datastore.SessionManager
import com.cmc.mytravelcompany.data.response.toDomain
import com.cmc.mytravelcompany.domain.entity.UserEntity
import com.cmc.mytravelcompany.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrofitAuthRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val sessionManager: SessionManager
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<UserEntity> {
        return try {
            // Simulamos el login con tu API actual
            val response = api.doLogin()
            // Tomamos uno al azar como hacías antes y lo pasamos a dominio
            val user = response.random().toDomain()
            
            // EL REPOSITORIO GUARDA LA SESIÓN
            sessionManager.saveUser(user)
            
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        sessionManager.logout()
    }

    override val currentUser: Flow<UserEntity?> = sessionManager.userData

    override suspend fun signUp(email: String, password: String, name: String?):
    Result<UserEntity> {
        return Result.failure(Exception("Registro no implementado en Retrofit"))
    }
}
