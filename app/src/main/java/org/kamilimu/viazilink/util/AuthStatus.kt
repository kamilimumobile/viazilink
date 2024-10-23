package org.kamilimu.viazilink.util

sealed class AuthStatus {
    object Authenticated: AuthStatus()
    object Unauthenticated: AuthStatus()  // Status when user is logged out
    object Loading: AuthStatus()
    data class Error(val message: String): AuthStatus()
}