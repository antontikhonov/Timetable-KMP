package ru.antontikhonov.timetable_kmp.features.timetable.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.antontikhonov.timetable_kmp.resources.Colors

@Composable
internal fun LessonTile(
    time: String,
    oddLesson: String?,
    evenLesson: String?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(color = Colors.BLACK_TRANSPARENT)
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp),
            text = time,
            fontSize = 12.sp,
            color = Color.White,
        )
        Column {
            oddLesson?.let {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = oddLesson,
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }
            if (oddLesson != null && evenLesson != null) {
                Divider(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .height(2.dp),

                    color = Colors.DIRTY_YELLOW,
                )
            }
            evenLesson?.let {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = evenLesson,
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }
        }
    }
}
