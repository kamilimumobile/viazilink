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
import org.kamilimu.viazilink.farmer.domain.repository.FarmerRepository
import org.kamilimu.viazilink.util.ScreenViewState
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository
) : ViewModel() {

    private val _homeUiState = MutableStateFlow<ScreenViewState>(ScreenViewState.Loading)
    val homeUiState: StateFlow<ScreenViewState> = _homeUiState
        .onStart { getFarmerOrders() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ScreenViewState.Loading
        )

    /**
     * Fetches all orders from the database
     * This is automatically invoked when the screen is in focus
     */
    private fun getFarmerOrders() = viewModelScope.launch {
        farmerRepository.getFarmerOrders()
            .onRight { orders ->
                _homeUiState.value = ScreenViewState.Success(orders)
            }.onLeft { firestoreError ->
                _homeUiState.value = ScreenViewState.Failure(firestoreError.error)
            }
    }
}