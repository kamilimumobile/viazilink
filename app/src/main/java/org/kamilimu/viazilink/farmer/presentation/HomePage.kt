package org.kamilimu.viazilink.farmer.presentation

import android.content.res.Configuration
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(scrollBehavior: TopAppBarScrollBehavior) {
    HomePageContent(
        scrollBehavior = scrollBehavior,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_small))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomePageContent(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
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
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        )
    }
}