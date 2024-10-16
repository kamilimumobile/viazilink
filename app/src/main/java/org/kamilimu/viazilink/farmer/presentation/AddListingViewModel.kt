package org.kamilimu.viazilink.farmer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.kamilimu.viazilink.farmer.domain.repository.FarmerRepository
import javax.inject.Inject

@HiltViewModel
class AddListingViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository
) : ViewModel() {

    /**
     * Invoked when the Post button in `AddListingPage` is clicked
     * Posts the new listing to the Firestore database
     */
    fun onPostListing(
        typeOfPotato: String,
        quantity: Int,
        price: Int,
        deliveryTimeFrame: Int
    ) = viewModelScope.launch {  }
}