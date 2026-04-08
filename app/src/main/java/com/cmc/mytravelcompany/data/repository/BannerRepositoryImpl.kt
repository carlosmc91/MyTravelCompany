package com.cmc.mytravelcompany.data.repository

import com.cmc.mytravelcompany.data.datasource.datastore.CacheManager
import com.cmc.mytravelcompany.data.datasource.db.dao.BannerDao
import com.cmc.mytravelcompany.data.datasource.db.entity.toDb
import com.cmc.mytravelcompany.data.datasource.db.entity.toDomain
import com.cmc.mytravelcompany.domain.entity.BannerEntity
import com.cmc.mytravelcompany.domain.repository.BannerRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val bannerDao: BannerDao,
    private val cacheManager: CacheManager
) : BannerRepository {
    
    override fun getBanners(): Flow<List<BannerEntity>> {
        return bannerDao.getAllBanners()
            .map { list -> list.map { it.toDomain() } }
            .onStart {
                // Solo refrescamos si ha pasado el tiempo de caché
                if (cacheManager.shouldRefreshBanners()) {
                    try {
                        refreshBannersFromRemote()
                        cacheManager.updateBannersTimestamp()
                    } catch (e: Exception) {
                        // Error de red, no hacemos nada, Room seguirá sirviendo los datos
                    }
                }
            }
    }

    private suspend fun refreshBannersFromRemote() {
        val snapshot = firestore.collection("banners")
            .orderBy("priority")
            .get()
            .await()

        val remoteBanners = snapshot.documents.mapNotNull { doc ->
            BannerEntity(
                id = doc.id,
                imageUrl = doc.getString("imageUrl") ?: "",
                title = doc.getString("title") ?: "",
                subtitle = doc.getString("subtitle") ?: "",
                priority = doc.getLong("priority")?.toInt() ?: 0
            )
        }

        bannerDao.deleteAllBanners()
        bannerDao.insertBanners(remoteBanners.map { it.toDb() })
    }
}
