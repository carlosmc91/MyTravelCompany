package com.cmc.mytravelcompany.data.datasource.api

import com.cmc.mytravelcompany.data.response.BannerResponse
import com.cmc.mytravelcompany.data.response.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("doLogin/.json")
    suspend fun doLogin(): List<UserResponse>

    @GET("banners/.json")
    suspend fun getBanners(): Map<String, BannerResponse>
}
