package com.cmc.mytravelcompany.data.di

import com.cmc.mytravelcompany.data.datasource.api.ApiService
import com.cmc.mytravelcompany.data.repository.BannerRepositoryImpl
import com.cmc.mytravelcompany.data.repository.FirebaseAuthRepositoryImpl
import com.cmc.mytravelcompany.data.repository.LocalBenefitRepository
import com.cmc.mytravelcompany.domain.repository.AuthRepository
import com.cmc.mytravelcompany.domain.repository.BannerRepository
import com.cmc.mytravelcompany.domain.repository.BenefitRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
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
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: FirebaseAuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindBannerRepository(
        impl: BannerRepositoryImpl
    ): BannerRepository

    @Binds
    @Singleton
    abstract fun bindBenefitRepository(
        impl: LocalBenefitRepository
    ): BenefitRepository

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

        @Provides
        @Singleton
        fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance("mytravelcompany")

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
}
