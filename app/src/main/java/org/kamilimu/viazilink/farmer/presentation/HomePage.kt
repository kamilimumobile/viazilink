package org.kamilimu.viazilink.farmer.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.farmer.domain.model.Order
import org.kamilimu.viazilink.farmer.presentation.components.OrderCard
import org.kamilimu.viazilink.ui.theme.AppTheme
import org.kamilimu.viazilink.util.ScreenViewState
import org.kamilimu.viazilink.util.components.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    scrollBehavior: TopAppBarScrollBehavior,
    homePageViewModel: HomePageViewModel = hiltViewModel()
) {
    val screenState by homePageViewModel.homeUiState.collectAsStateWithLifecycle()

    HomePageContent(
        scrollBehavior = scrollBehavior,
        screenState = screenState,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_small))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomePageContent(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    screenState: ScreenViewState
) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        state = scrollState
    ) {
        item {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_extra_small)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Text(text = "Hello Kristian,")
                        Text(text = "Welcome Back")
                    }
                    Spacer(modifier.weight(1f))
                    Text(
                        text = "3.8" /*TODO: Get the farmer rating*/
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_extra_small))
            ) {
                Text(
                    text = stringResource(R.string.recent_orders)
                )
            }
        }
        /*TODO: Fetch a list of orders for a farmer*/
        item {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (screenState) {
                    is ScreenViewState.Loading -> {
                        LoadingIndicator()
                    }

                    is ScreenViewState.Success<*> -> {
                        val orders = screenState.data as? List<Order>
                        orders?.let {
                            orders.forEach { order ->
                                OrderCard(order = order)
                            }
                        }
                    }

                    is ScreenViewState.Failure -> {
                        Text(
                            text = screenState.message,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    else -> Unit
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "HomePageLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "HomePageDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomePagePreview() {
    AppTheme {
        HomePageContent(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            screenState = ScreenViewState.Failure("No Records Found"),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        )
    }
}