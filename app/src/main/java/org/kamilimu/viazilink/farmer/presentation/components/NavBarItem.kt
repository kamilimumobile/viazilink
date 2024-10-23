package org.kamilimu.viazilink.farmer.presentation.components

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import org.kamilimu.viazilink.util.ScreenNames

data class NavBarItem(
    val screenName: ScreenNames,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector,
    @StringRes val label: Int
)
