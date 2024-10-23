package org.kamilimu.viazilink.farmer.presentation.components

import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.kamilimu.viazilink.util.ScreenNames

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navBarItems: List<NavBarItem>,
    currentScreen: ScreenNames,
    onItemSelected: (ScreenNames) -> Unit
) {
    NavigationBar(modifier = modifier) {
        navBarItems.forEach { item ->
            NavigationBarItem(
                selected = currentScreen == item.screenName,
                onClick = { onItemSelected(item.screenName) },
                icon = {
                    BadgedBox(badge = {}) {
                        Icon(
                            imageVector = if (currentScreen == item.screenName) item.filledIcon else item.outlinedIcon,
                            contentDescription = stringResource(item.label)
                        )
                    }
                },
                label = {
                    Text(text = stringResource(item.label))
                }
            )
        }
    }
}