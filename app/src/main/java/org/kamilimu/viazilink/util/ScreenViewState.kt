package org.kamilimu.viazilink.util

sealed interface ScreenViewState {
    object Loading: ScreenViewState
    data class Success <T> (val data: T): ScreenViewState
    data class Failure(val message: String): ScreenViewState
    object PreActivity: ScreenViewState
}