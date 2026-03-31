package com.cmc.mytravelcompany.data.repository

import android.util.Log
import com.cmc.mytravelcompany.data.datasource.api.ApiService
import com.cmc.mytravelcompany.data.response.UserResponse
import com.cmc.mytravelcompany.data.response.toDomain
import com.cmc.mytravelcompany.domain.entity.UserEntity
import com.cmc.mytravelcompany.domain.repository.AuthRepository

class AuthRepositoryImpl(private val api: ApiService) : AuthRepository {
    override suspend fun doLogin(user: String, password: String): List<UserEntity> {
        val response: List<UserResponse> = try {
            api.doLogin()
        } catch (e: Exception) {
            Log.i("ERROR", e.message.toString())
            listOf()
        }
        Log.i("Hola", response.size.toString())
        return response.map { it.toDomain() }
    }
}
