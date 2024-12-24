package ru.antontikhonov.timetable_kmp.settings.main.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import ru.antontikhonov.timetable_kmp.resources.Colors

@Composable
internal fun SettingsItem(
    iconResource: DrawableResource,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(color = Colors.BLACK_TRANSPARENT)
            .padding(vertical = 16.dp, horizontal = 36.dp)
            .clickable {
                onClick()
            },
    ) {
        Icon(
            painter = painterResource(iconResource),
            tint = Color.White,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = text,
            color = Color.White,
        )
    }
}
