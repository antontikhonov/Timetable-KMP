package ru.antontikhonov.timetable_kmp.features.timetable.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.antontikhonov.timetable_kmp.domain.entities.DayEntity

@Composable
internal fun DayTabItem(
    dayEntity: DayEntity?,
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
}
