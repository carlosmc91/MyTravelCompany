package com.cmc.mytravelcompany.view.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.imageLoader
import coil.request.ImageRequest
import com.cmc.mytravelcompany.domain.entity.BannerEntity
import com.cmc.mytravelcompany.domain.entity.DestinationEntity
import com.cmc.mytravelcompany.domain.entity.UserEntity
import com.cmc.mytravelcompany.domain.usecase.GetBannersUseCase
import com.cmc.mytravelcompany.domain.usecase.GetDestinationsUseCase
import com.cmc.mytravelcompany.domain.usecase.GetSession
import com.cmc.mytravelcompany.domain.usecase.Logout
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSession: GetSession,
    private val logOut: Logout,
    private val getBannersUseCase: GetBannersUseCase,
    private val getDestinationsUseCase: GetDestinationsUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState: StateFlow<MainUiState> = _mainUiState.asStateFlow()

    init {
        loadUserSession()
        loadBanners()
        loadDestinations()
    }

    private fun loadUserSession() {
        viewModelScope.launch {
            getSession().collect { user ->
                _mainUiState.update { it.copy(user = user) }
            }
        }
    }

    private fun loadBanners() {
        viewModelScope.launch {
            _mainUiState.update { it.copy(isLoadingBanners = true) }
            try {
                // Ahora es una función suspend, se asigna el resultado directamente
                val banners = getBannersUseCase()
                _mainUiState.update {
                    it.copy(
                        banners = banners,
                        isLoadingBanners = false
                    )
                }
                preloadImages(banners)
            } catch (e: Exception) {
                _mainUiState.update { it.copy(isLoadingBanners = false) }
                Log.e("MainViewModel", "Error loading banners", e)
            }
        }
    }

    private fun loadDestinations() {
        viewModelScope.launch {
            try {
                val destinations = getDestinationsUseCase()
                _mainUiState.update { it.copy(destinations = destinations) }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading destinations", e)
            }
        }
    }

    private fun preloadImages(banners: List<BannerEntity>) {
        banners.forEach { banner ->
            val request = ImageRequest.Builder(context)
                .data(banner.imageUrl)
                .build()
            context.imageLoader.enqueue(request)
        }
    }

    fun onLogout() {
        viewModelScope.launch {
            logOut()
        }
    }
}

data class MainUiState(
    val user: UserEntity? = null,
    val banners: List<BannerEntity> = emptyList(),
    val destinations: List<DestinationEntity> = emptyList(),
    val isLoadingBanners: Boolean = false
)
