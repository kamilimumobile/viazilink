package org.kamilimu.viazilink.farmer.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.ui.theme.AppTheme
import org.kamilimu.viazilink.util.AuthStatus

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    authStatus: AuthStatus,
    toFarmerScreens: () -> Unit,
    toAuthScreens: () -> Unit
) {
    LaunchedEffect(authStatus) {
        when (authStatus) {
            is AuthStatus.Authenticated -> toFarmerScreens()
            is AuthStatus.Unauthenticated -> toAuthScreens()
            else -> Unit
        }
    }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceTint,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.viazilink),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 50.sp)
            )
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(name = "StartScreenLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "StartScreenDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StartScreenPreview() {
    AppTheme {
        StartScreen(
            modifier = Modifier.fillMaxSize(),
            authStatus = AuthStatus.Loading,
            toFarmerScreens = {},
            toAuthScreens = {}
        )
    }
}