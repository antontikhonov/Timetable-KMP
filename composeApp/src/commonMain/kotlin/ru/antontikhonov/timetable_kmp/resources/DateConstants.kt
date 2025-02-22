package ru.antontikhonov.timetable_kmp.resources

import org.jetbrains.compose.resources.StringResource
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.small_friday
import timetable_kmp.composeapp.generated.resources.small_monday
import timetable_kmp.composeapp.generated.resources.small_saturday
import timetable_kmp.composeapp.generated.resources.small_thursday
import timetable_kmp.composeapp.generated.resources.small_tuesday
import timetable_kmp.composeapp.generated.resources.small_wednesday

internal object DateConstants {

    fun getSmallMonth(index: Int): StringResource {
        return arrayOf(
            Res.string.small_monday,
            Res.string.small_tuesday,
            Res.string.small_wednesday,
            Res.string.small_thursday,
            Res.string.small_friday,
            Res.string.small_saturday,
        )[index]
    }
}