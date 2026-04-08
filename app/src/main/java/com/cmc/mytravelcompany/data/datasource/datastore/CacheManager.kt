package com.cmc.mytravelcompany.data.datasource.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "cache_metadata")

    companion object {
        private val BANNERS_LAST_UPDATE = longPreferencesKey("banners_last_update")
        private const val CACHE_DURATION = 60 * 60 * 1000 // 1 hora en milisegundos
    }

    suspend fun shouldRefreshBanners(): Boolean {
        val lastUpdate = context.dataStore.data.map { preferences ->
            preferences[BANNERS_LAST_UPDATE] ?: 0L
        }.first()

        val currentTime = System.currentTimeMillis()
        return (currentTime - lastUpdate) > CACHE_DURATION
    }

    suspend fun updateBannersTimestamp() {
        context.dataStore.edit { preferences ->
            preferences[BANNERS_LAST_UPDATE] = System.currentTimeMillis()
        }
    }
}
