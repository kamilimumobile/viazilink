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
class ProduceListingsViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository
) : ViewModel() {

    private val _listingsUiState = MutableStateFlow<ScreenViewState>(ScreenViewState.Loading)
    val listingsUiState: StateFlow<ScreenViewState> = _listingsUiState
        .onStart { getFarmerListings() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ScreenViewState.Loading
        )

    /**
     * Fetches all listings that have been posted by a farmer, from the database
     * It updates the value of the StateFlow that is being observed by a view
     */
    private fun getFarmerListings() = viewModelScope.launch {
        _listingsUiState.value = ScreenViewState.Loading
        farmerRepository.getFarmerOrders()
            .onRight { orders ->
                _listingsUiState.value = ScreenViewState.Success(orders)
            }.onLeft { firestoreError ->
                _listingsUiState.value = ScreenViewState.Failure(firestoreError.error)
            }
    }
}