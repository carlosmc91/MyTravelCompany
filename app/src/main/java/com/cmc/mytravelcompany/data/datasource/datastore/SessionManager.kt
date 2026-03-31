package com.cmc.mytravelcompany.data.datasource.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cmc.mytravelcompany.domain.entity.UserEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "user_session")

    companion object {
        private val USER_DATA_KEY = stringPreferencesKey("user_data")
    }

    val userData: Flow<UserEntity?> = context.dataStore.data.map { preferences ->
        val userJson = preferences[USER_DATA_KEY]
        if (userJson != null) {
            try {
                Json.decodeFromString<UserEntity>(userJson)
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

    suspend fun saveUser(user: UserEntity) {
        val userJson = Json.encodeToString(user)
        context.dataStore.edit { preferences ->
            preferences[USER_DATA_KEY] = userJson
        }
    }

    suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_DATA_KEY)
        }
    }
}
