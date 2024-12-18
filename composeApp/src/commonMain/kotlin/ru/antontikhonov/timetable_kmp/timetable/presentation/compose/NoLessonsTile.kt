package ru.antontikhonov.timetable_kmp.timetable.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.resources.Colors
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.no_classes

@Composable
internal fun NoLessonsTile() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(color = Colors.BLACK_TRANSPARENT)
            .padding(16.dp),
        text = stringResource(Res.string.no_classes),
        color = Color.White,
        textAlign = TextAlign.Center,
    )
}
