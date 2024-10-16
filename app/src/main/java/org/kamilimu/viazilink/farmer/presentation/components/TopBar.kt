package org.kamilimu.viazilink.farmer.presentation.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.ui.theme.AppTheme
import org.kamilimu.viazilink.util.ScreenNames

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    currentScreen: ScreenNames,
    onBackClicked: () -> Unit,
    scrollBehaviour: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentScreen.route,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        navigationIcon = {
            if (currentScreen != ScreenNames.HomeScreen) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        scrollBehavior = scrollBehaviour,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "TopBarLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "TopBarDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TopBarPreview() {
    AppTheme {
        TopBar(
            currentScreen = ScreenNames.HomeScreen,
            onBackClicked = {},
            scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
        )
    }
}
