package org.kamilimu.viazilink.client.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun NavBarItemComponent(item: NavBarItem, isSelected: Boolean, onClick: () -> Unit) {
    Icon(
        imageVector = item.icon,
        contentDescription = item.label
    )
    Text(text = item.label)
}
