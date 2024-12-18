package ru.antontikhonov.timetable_kmp

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ru.antontikhonov.timetable_kmp.resources.Colors

@Composable
internal fun RowScope.TimetableBottomNavigationItem(
    selected: Boolean,
    labelText: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    BottomNavigationItem(
        unselectedContentColor = Color.White,
        selectedContentColor = Colors.DIRTY_YELLOW,
        selected = selected,
        onClick = onClick,
        icon = { Icon(icon, contentDescription = null) },
        label = { Text(labelText) },
        interactionSource = remember { NoRippleInteractionSource() }
    )
}

private class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}
