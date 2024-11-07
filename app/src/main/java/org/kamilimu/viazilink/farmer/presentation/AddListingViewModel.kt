package org.kamilimu.viazilink.farmer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.kamilimu.viazilink.farmer.domain.model.Listing
import org.kamilimu.viazilink.farmer.domain.repository.FarmerRepository
import org.kamilimu.viazilink.util.ScreenViewState
import javax.inject.Inject

@HiltViewModel
class AddListingViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository
) : ViewModel() {

    private val _listingScreenState = MutableStateFlow<ScreenViewState>(ScreenViewState.PreActivity)
    val listingScreenState: StateFlow<ScreenViewState> = _listingScreenState
        .onStart { }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ScreenViewState.PreActivity
        )

    /**
     * Invoked when the Post button in `AddListingPage` is clicked
     * Posts the new listing to the Firestore database
     */
    fun onPostListing(listing: Listing) = viewModelScope.launch {
        _listingScreenState.value = ScreenViewState.Loading
        delay(1000)
        farmerRepository.addListing(listing)
            .onRight { listing ->
                _listingScreenState.value = ScreenViewState.Success(listing)
            }.onLeft { fireStoreError ->
                _listingScreenState.value = ScreenViewState.Failure(fireStoreError.error)
            }
    }

    /**
     * Invoked when the user clicks the `Cancel` text button on the Failure Screen
     * The [ScreenViewState] is changed to `PreActivity`
     */
    fun onCancel() = viewModelScope.launch {
        _listingScreenState.value = ScreenViewState.Loading
        delay(1500)
        _listingScreenState.value = ScreenViewState.PreActivity
    }
}