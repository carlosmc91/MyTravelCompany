package com.cmc.mytravelcompany.data.repository

import com.cmc.mytravelcompany.domain.entity.UserEntity
import com.cmc.mytravelcompany.domain.entity.UserMode
import com.cmc.mytravelcompany.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<UserEntity> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user ?: throw Exception("Usuario no encontrado")
            Result.success(firebaseUser.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String, name: String?):
            Result<UserEntity> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user ?: throw Exception("Error al crear el usuario")
            Result.success(firebaseUser.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override val currentUser: Flow<UserEntity?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser?.toDomain())
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }

    private fun com.google.firebase.auth.FirebaseUser.toDomain(): UserEntity {
        return UserEntity(
            userId = uid,
            name = displayName ?: "Viajero",
            nickname = email?.substringBefore("@") ?: "usuario",
            followers = 0,
            following = emptyList(),
            userMode = UserMode.REGULAR_USER
        )
    }
}
