package com.cmc.mytravelcompany.data.response

import com.cmc.mytravelcompany.domain.entity.BannerEntity
import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    val id: String,
    val imageUrl: String,
    val title: String,
    val subtitle: String,
    val priority: Int
)

fun BannerResponse.toDomain() = BannerEntity(
    id = id,
    imageUrl = imageUrl,
    title = title,
    subtitle = subtitle,
    priority = priority
)
