package com.cmc.mytravelcompany.data.repository

import com.cmc.mytravelcompany.domain.entity.BannerEntity
import com.cmc.mytravelcompany.domain.repository.BannerRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : BannerRepository {
    override suspend fun getBanners(): Result<List<BannerEntity>> {
        return try {
            val snapshot = firestore.collection("banners")
                .orderBy("priority")
                .get()
                .await()
            
            val banners = snapshot.documents.mapNotNull { doc ->
                BannerEntity(
                    id = doc.id,
                    imageUrl = doc.getString("imageUrl") ?: "",
                    title = doc.getString("title") ?: "",
                    subtitle = doc.getString("subtitle") ?: "",
                    priority = doc.getLong("priority")?.toInt() ?: 0
                )
            }
            Result.success(banners)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
