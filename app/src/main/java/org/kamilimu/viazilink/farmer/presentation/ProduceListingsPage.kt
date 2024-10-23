@file:OptIn(ExperimentalMaterial3Api::class)

package org.kamilimu.viazilink.farmer.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.farmer.domain.model.Listing
import org.kamilimu.viazilink.farmer.presentation.components.ListingCard
import org.kamilimu.viazilink.ui.theme.AppTheme
import org.kamilimu.viazilink.util.ScreenViewState
import org.kamilimu.viazilink.util.components.LoadingIndicator

@Composable
fun ProduceListingsPage(
    scrollBehavior: TopAppBarScrollBehavior,
    listingsPageViewModel: ProduceListingsViewModel = hiltViewModel()
) {
    val screenState by listingsPageViewModel.listingsUiState.collectAsStateWithLifecycle()

    ProduceListingsContent(
        scrollBehavior = scrollBehavior,
        screenState = screenState,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_small))
    )
}

@Composable
private fun ProduceListingsContent(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    screenState: ScreenViewState
) {
    val scrollState = rememberLazyListState()

    when (screenState) {
        is ScreenViewState.Loading -> {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingIndicator()
            }
        }

        is ScreenViewState.Failure -> {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = screenState.message,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        is ScreenViewState.Success<*> -> {
            LazyColumn(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                state = scrollState
            ) {
                val listings = screenState.data as List<Listing>
                items(listings) { listing ->
                    ListingCard(listing = listing)
                }
            }
        }

        else -> Unit
    }
}

@Preview(
    name = "ProduceListingsLight",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "ProduceListingsDark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProduceListingsPreview() {
    AppTheme {
        ProduceListingsContent(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            screenState = ScreenViewState.Failure("No Records Found"),
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_small))
        )
    }
}