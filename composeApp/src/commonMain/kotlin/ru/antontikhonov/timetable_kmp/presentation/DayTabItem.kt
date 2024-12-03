package ru.antontikhonov.timetable_kmp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.networking.entities.DayEntity
import ru.antontikhonov.timetable_kmp.resources.Colors
import ru.antontikhonov.timetable_kmp.util.NetworkError
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.error_message

@Composable
internal fun DayTabItem(
    dayEntity: DayEntity?,
    isLoading: Boolean,
    error: NetworkError?,
) {
    if (dayEntity != null) {
        val scrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(16.dp),
        ) {
            if (dayEntity.classes.isNotEmpty()) {
                dayEntity.classes.forEach { klass ->
                    LessonTile(
                        time = klass.time,
                        oddLesson = klass.odd,
                        evenLesson = klass.even,
                    )
                }
            } else {
                NoLessonsTile()
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(25.dp),
                strokeWidth = 2.dp,
                color = Color.White,
            )
        }
        error?.let {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(Colors.DARK_BLACK_TRANSPARENT)
                    .padding(16.dp),
                text = stringResource(Res.string.error_message, error.name),
                color = Color.Red,
                textAlign = TextAlign.Center,
            )
        }
    }
}
