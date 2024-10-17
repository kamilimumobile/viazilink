@file:OptIn(ExperimentalMaterial3Api::class)

package org.kamilimu.viazilink.farmer.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.ui.theme.AppTheme

@Composable
fun ProduceListingsPage(scrollBehavior: TopAppBarScrollBehavior) {
    ProduceListingsContent(
        scrollBehavior = scrollBehavior,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_small))
    )
}

@Composable
private fun ProduceListingsContent(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        state = scrollState
    ) {
        /*TODO: Fetch all listings posted by a farmer*/
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
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_small))
        )
    }
}