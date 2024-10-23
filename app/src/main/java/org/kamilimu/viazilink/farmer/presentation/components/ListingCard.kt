package org.kamilimu.viazilink.farmer.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.farmer.domain.model.Listing
import org.kamilimu.viazilink.ui.theme.AppTheme

@Composable
fun ListingCard(
    modifier: Modifier = Modifier,
    listing: Listing
) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = listing.imageUrl ?: R.drawable.listing_image_placeholder,
                contentDescription = stringResource(R.string.image_description),
                placeholder = painterResource(R.drawable.listing_image_placeholder),
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.listing_image_placeholder),
                modifier = Modifier.size(150.dp)
            )
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = listing.type)
                Text(text = "Ksh: ${listing.price}")
                Text(text = "Quantity: ${listing.quantity}")
            }
        }
    }
}

@Preview(name = "ListingCardLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "ListingCardDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ListingCardPreview() {
    AppTheme {
        ListingCard(
            modifier = Modifier.fillMaxWidth(),
            listing = Listing("Irish Potatoes", 50, 10000, 2)
        )
    }
}