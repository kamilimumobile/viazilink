package org.kamilimu.viazilink.farmer.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.farmer.domain.model.Order
import org.kamilimu.viazilink.ui.theme.AppTheme

@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    order: Order
) {
    Card(modifier = modifier) {
        Column {
            AsyncImage(
                model = order.imageUrl,
                contentDescription = stringResource(R.string.image_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(180.dp)
            )
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_small))
            ) {
                Text(
                    text = order.typeOfPotato,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = "#${order.id}",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.customerId,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = order.orderTime,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = order.quantity.toString(),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Ksh ${order.price}",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Preview(name = "OrderCardLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "OrderCardDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OrderCardPrev() {
    AppTheme {
        OrderCard(
            order = Order("orderId", "customerId", "123456", 50, "Irish Potato", "", 5000),
            modifier = Modifier.wrapContentSize()
        )
    }
}