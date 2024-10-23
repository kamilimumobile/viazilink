package org.kamilimu.viazilink.farmer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.kamilimu.viazilink.farmer.domain.repository.AuthRepository
import org.kamilimu.viazilink.util.AuthStatus
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authStatus = MutableStateFlow<AuthStatus>(AuthStatus.Unauthenticated)
    val authStatus: StateFlow<AuthStatus> = _authStatus
        .onStart { checkLoginStatus() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AuthStatus.Unauthenticated
        )

    private fun checkLoginStatus() = viewModelScope.launch {
        _authStatus.value = authRepository.checkLoginStatus()
    }

    /**
     * Invoked when login button on `LoginScreen` is clicked
     */
    fun onLogin(email: String, password: String) = viewModelScope.launch {
        _authStatus.value = AuthStatus.Loading
        authRepository.userLogin(email, password).also { authStatus ->
            _authStatus.value = authStatus
        }
    }

    /**
     * Invoked when signup button on `SignUpScreen` is clicked
     */
    fun onSignUp(email: String, password: String) = viewModelScope.launch {
        _authStatus.value = AuthStatus.Loading
        authRepository.userSignUp(email, password).also { authStatus ->
            _authStatus.value = authStatus
        }
    }

    /**
     * Invoked when user logs out of the app
     */
    fun onLogout() {
        _authStatus.value = authRepository.userLogout()
    }
}