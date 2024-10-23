package org.kamilimu.viazilink.farmer.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.farmer.domain.model.Listing
import org.kamilimu.viazilink.ui.theme.AppTheme
import org.kamilimu.viazilink.util.ScreenViewState
import org.kamilimu.viazilink.util.components.LoadingIndicator

@Composable
fun AddListingPage(addListingViewModel: AddListingViewModel = hiltViewModel()) {

    val screenState by addListingViewModel.listingScreenState.collectAsStateWithLifecycle()

    AddListingContent(
        onPostListing = addListingViewModel::onPostListing,
        screenState = screenState,
        onCancel = addListingViewModel::onCancel,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_small))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddListingContent(
    modifier: Modifier = Modifier,
    screenState: ScreenViewState,
    onPostListing: (Listing) -> Unit,
    onCancel: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val potatoOptions = listOf("Irish Potatoes", "Sweet Potatoes", "Cassava", "Arrowroot")
    var expanded by rememberSaveable { mutableStateOf(false) }
    var typeOfPotato by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var deliveryTimeFrame by rememberSaveable { mutableStateOf("") }
    var newListing by rememberSaveable { mutableStateOf(Listing("", 0, 0, 0)) }

    when (screenState) {
        is ScreenViewState.PreActivity -> {
            LazyColumn(modifier = modifier) {
                item {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium)),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(dimensionResource(R.dimen.padding_extra_small)),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = stringResource(R.string.type_of_potato))
                                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_extra_small)))
                                ExposedDropdownMenuBox(
                                    expanded = expanded,
                                    onExpandedChange = { expanded = !it }
                                ) {
                                    OutlinedTextField(
                                        value = typeOfPotato,
                                        onValueChange = { typeOfPotato = it },
                                        label = {
                                            Text(
                                                text = stringResource(R.string.select_an_option),
                                                color = MaterialTheme.colorScheme.onSecondaryContainer
                                            )
                                        },
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expanded
                                            )
                                        }
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        potatoOptions.forEach { option ->
                                            DropdownMenuItem(
                                                text = { Text(text = option) },
                                                onClick = {
                                                    typeOfPotato = option
                                                    expanded = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(dimensionResource(R.dimen.padding_extra_small)),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = stringResource(R.string.quantity))
                                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_extra_small)))
                                OutlinedTextField(
                                    value = quantity,
                                    onValueChange = { quantity = it },
                                    singleLine = true,
                                    isError = quantity.toIntOrNull() == null && quantity.isNotEmpty(),
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                                    keyboardActions = KeyboardActions(
                                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(dimensionResource(R.dimen.padding_extra_small)),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = stringResource(R.string.price_per_kg))
                                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_extra_small)))
                                OutlinedTextField(
                                    value = price,
                                    onValueChange = { price = it },
                                    singleLine = true,
                                    isError = price.toIntOrNull() == null && price.isNotEmpty(),
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                                    keyboardActions = KeyboardActions(
                                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(dimensionResource(R.dimen.padding_extra_small)),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = stringResource(R.string.delivery_timeframe))
                                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_extra_small)))
                                OutlinedTextField(
                                    value = deliveryTimeFrame,
                                    onValueChange = { deliveryTimeFrame = it },
                                    singleLine = true,
                                    isError = deliveryTimeFrame.toIntOrNull() == null && deliveryTimeFrame.isNotEmpty(),
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                    keyboardActions = KeyboardActions(
                                        onDone = { focusManager.clearFocus() }
                                    )
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.padding_small)),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                try {
                                    newListing = Listing(
                                        typeOfPotato,
                                        quantity.toInt(),
                                        price.toInt(),
                                        deliveryTimeFrame.toInt()
                                    )
                                    onPostListing(newListing)
                                } catch (e: NumberFormatException) {
                                    Toast.makeText(
                                        context,
                                        "One of the fields has an invalid entry",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        ) {
                            Text(text = stringResource(R.string.post_listing))
                        }
                    }
                }
            }
        }

        is ScreenViewState.Loading -> {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingIndicator()
            }
        }

        is ScreenViewState.Success<*> -> {
            val listing = screenState.data as Listing
            Surface(
                modifier = modifier.wrapContentSize(Alignment.TopStart),
                shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium)),
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))) {
                    Text(
                        text = stringResource(R.string.success_message),
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp)
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_extra_small)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${stringResource(R.string.type_of_potato)}:")
                        Spacer(modifier = Modifier.weight(0.94f))
                        Text(text = listing.type, modifier = Modifier.weight(1f))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_extra_small)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${stringResource(R.string.quantity)}:")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = listing.quantity.toString(), modifier = Modifier.weight(1f))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_extra_small)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(R.string.price_per_kg))
                        Spacer(modifier = Modifier.weight(1.13f))
                        Text(text = listing.price.toString(), modifier = Modifier.weight(1f))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_extra_small)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${stringResource(R.string.delivery_timeframe)}:")
                        Spacer(modifier = Modifier.weight(0.5f))
                        Text(
                            text = listing.deliveryTimeframe.toString(),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        is ScreenViewState.Failure -> {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = screenState.message,
                    style = MaterialTheme.typography.titleLarge
                )
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(
                        onClick = { onPostListing(newListing) }
                    ) {
                        Text(text = stringResource(R.string.try_again))
                    }
                    TextButton(onClick = onCancel) {
                        Text(text = stringResource(R.string.cancel))
                    }
                }
            }
        }
    }
}

@Preview(name = "AddListingLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "AddListingDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AddListingContentPreview() {
    AppTheme {
        AddListingContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_small)),
            /*screenState = ScreenViewState.Success(
                Listing("Irish Potatoes", 50, 5000, 10)
            ),*/
            screenState = ScreenViewState.Failure("Network Error"),
            onPostListing = { _ -> },
            onCancel = {}
        )
    }
}