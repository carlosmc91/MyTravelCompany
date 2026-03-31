package com.cmc.mytravelcompany.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val userId: String,
    val name: String,
    val nickname: String,
    val followers: Int,
    val following: List<String>,
    val userMode: UserMode
)

@Serializable
sealed class UserMode(val userType: Int) {
    @Serializable object REGULAR_USER : UserMode(0)
    @Serializable object CONTENT_CREATOR_USER : UserMode(1)
    @Serializable object COMPANY_USER : UserMode(2)
}
