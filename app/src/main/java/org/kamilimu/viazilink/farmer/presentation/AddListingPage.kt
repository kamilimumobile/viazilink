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
import androidx.hilt.navigation.compose.hiltViewModel
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.ui.theme.AppTheme

@Composable
fun AddListingPage(addListingViewModel: AddListingViewModel = hiltViewModel()) {
    AddListingContent(
        onPostListing = addListingViewModel::onPostListing,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_small))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddListingContent(
    modifier: Modifier = Modifier,
    onPostListing: (String, Int, Int, Int) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val potatoOptions = listOf("Irish Potatoes", "Sweet Potatoes", "Cassava", "Arrowroots")
    var expanded by rememberSaveable { mutableStateOf(false) }
    var typeOfPotato by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var deliverTimeframe by rememberSaveable { mutableStateOf("") }

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
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
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
                            value = deliverTimeframe,
                            onValueChange = { deliverTimeframe = it },
                            singleLine = true,
                            isError = deliverTimeframe.toIntOrNull() == null && deliverTimeframe.isNotEmpty(),
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
                            onPostListing(
                                typeOfPotato,
                                quantity.toInt(),
                                price.toInt(),
                                deliverTimeframe.toInt()
                            )
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

@Preview(name = "AddListingLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "AddListingDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AddListingContentPreview() {
    AppTheme {
        AddListingContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_small)),
            onPostListing = { _, _, _, _ -> }
        )
    }
}