package com.cmc.mytravelcompany.domain.entity

data class BannerEntity(
    val id: String,
    val imageUrl: String,
    val title: String,
    val subtitle: String,
    val priority: Int
)
