package com.cmc.mytravelcompany.data.di

import com.cmc.mytravelcompany.data.datasource.api.ApiService
import com.cmc.mytravelcompany.data.datasource.datastore.SessionManager
import com.cmc.mytravelcompany.data.repository.AuthRepositoryImpl
import com.cmc.mytravelcompany.data.repository.SessionRepositoryImpl
import com.cmc.mytravelcompany.domain.repository.AuthRepository
import com.cmc.mytravelcompany.domain.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(api: ApiService): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSessionRepository(sessionManager: SessionManager): SessionRepository {
        return SessionRepositoryImpl(sessionManager)
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }
        return Retrofit.Builder()
            .baseUrl("https://instadev-4ea39-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}
