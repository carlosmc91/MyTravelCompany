package com.cmc.mytravelcompany.data.datasource.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cmc.mytravelcompany.data.datasource.db.entity.BannerDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {
    @Query("SELECT * FROM banners ORDER BY priority ASC")
    fun getAllBanners(): Flow<List<BannerDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanners(banners: List<BannerDbEntity>): List<Long>

    @Query("DELETE FROM banners")
    suspend fun deleteAllBanners(): Int
}
