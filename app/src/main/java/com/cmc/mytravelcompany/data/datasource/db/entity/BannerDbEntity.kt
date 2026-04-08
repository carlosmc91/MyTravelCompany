package com.cmc.mytravelcompany.data.datasource.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cmc.mytravelcompany.domain.entity.BannerEntity

@Entity(tableName = "banners")
data class BannerDbEntity(
    @PrimaryKey val id: String,
    val imageUrl: String,
    val title: String,
    val subtitle: String,
    val priority: Int
)

fun BannerDbEntity.toDomain() = BannerEntity(
    id = id,
    imageUrl = imageUrl,
    title = title,
    subtitle = subtitle,
    priority = priority
)

fun BannerEntity.toDb() = BannerDbEntity(
    id = id,
    imageUrl = imageUrl,
    title = title,
    subtitle = subtitle,
    priority = priority
)
