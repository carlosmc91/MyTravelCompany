package com.cmc.mytravelcompany.view.core.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.mytravelcompany.domain.usecase.GetSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    getSession: GetSession
) : ViewModel() {
    val sessionState: StateFlow<SessionState> = getSession().map { user ->
        if (user != null) SessionState.Logged else SessionState.NotLogged
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SessionState.Loading
    )
}

sealed class SessionState {
    object Loading : SessionState()
    object Logged : SessionState()
    object NotLogged : SessionState()
}
