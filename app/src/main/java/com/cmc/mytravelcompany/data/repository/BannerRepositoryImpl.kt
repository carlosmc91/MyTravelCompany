package com.cmc.mytravelcompany.data.repository

import android.content.Context
import com.cmc.mytravelcompany.data.datasource.datastore.CacheManager
import com.cmc.mytravelcompany.data.datasource.db.dao.BannerDao
import com.cmc.mytravelcompany.data.datasource.db.entity.toDb
import com.cmc.mytravelcompany.data.datasource.db.entity.toDomain
import com.cmc.mytravelcompany.domain.entity.BannerEntity
import com.cmc.mytravelcompany.domain.repository.BannerRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val bannerDao: BannerDao,
    private val cacheManager: CacheManager,
    @ApplicationContext private val context: Context
) : BannerRepository {

    override fun getBanners(): Flow<List<BannerEntity>> {
        return bannerDao.getAllBanners()
            .map { list -> list.map { it.toDomain() } }
            .onStart {
                if (cacheManager.shouldRefreshBanners()) {
                    try {
                        refreshBannersFromRemote()
                        cacheManager.updateBannersTimestamp()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
    }

    private suspend fun refreshBannersFromRemote() {
        // Obtenemos lo que tenemos actualmente en Room para comparar
        val currentLocalBanners = bannerDao.getAllBanners().first().associateBy { it.id }

        val snapshot = firestore.collection("banners")
            .orderBy("priority")
            .get()
            .await()

        val remoteBanners = snapshot.documents.mapNotNull { doc ->
            val id = doc.id
            val remoteImageUrl = doc.getString("imageUrl") ?: ""
            val localBanner = currentLocalBanners[id]

            // Solo descargamos si la URL ha cambiado o no tenemos el archivo local
            val localPath = if (localBanner?.imageUrl != remoteImageUrl || localBanner.localPath == null) {
                downloadImageLocally(id, remoteImageUrl)
            } else {
                localBanner.localPath
            }

            BannerEntity(
                id = id,
                imageUrl = remoteImageUrl,
                title = doc.getString("title") ?: "",
                subtitle = doc.getString("subtitle") ?: "",
                priority = doc.getLong("priority")?.toInt() ?: 0,
                localPath = localPath
            )
        }

        bannerDao.deleteAllBanners()
        bannerDao.insertBanners(remoteBanners.map { it.toDb() })
    }

    private suspend fun downloadImageLocally(id: String, imageUrl: String): String? {
        if (imageUrl.isEmpty()) return null
        
        return withContext(Dispatchers.IO) {
            try {
                val directory = File(context.filesDir, "banners")
                if (!directory.exists()) directory.mkdirs()

                val file = File(directory, "banner_$id.webp")
                
                val url = URL(imageUrl)
                url.openStream().use { input ->
                    file.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
                file.absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
