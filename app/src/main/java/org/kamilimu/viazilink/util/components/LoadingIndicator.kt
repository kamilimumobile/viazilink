package org.kamilimu.viazilink.util.components

import android.content.res.Configuration
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.kamilimu.viazilink.ui.theme.AppTheme

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Preview(
    name = "LoadingIndicatorLight",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "LoadingIndicatorDark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LoadingIndicatorPrev() {
    AppTheme {
        LoadingIndicator()
    }
}