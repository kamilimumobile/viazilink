package org.kamilimu.viazilink.util.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kamilimu.viazilink.ui.theme.AppTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {
        Text(text = "Page", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            modifier = Modifier.padding(horizontal = 14.dp),
            text = page.title
        )
        Text(
            modifier = Modifier.padding(horizontal = 14.dp),
            text = page.description
        )
    }
}

@Preview(name = "OnBoardingLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "OnBoardingDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingPagePreview() {
    AppTheme() {
        OnBoardingPage(
            page = Page(
                title = "Lorem Ipsum is simply dummy",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry"
            )
        )
    }
}