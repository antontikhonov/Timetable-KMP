package ru.antontikhonov.timetable_kmp.timetable.presentation

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.timetable.domain.DayEntity

data class TimetableState(
    val numberOfGroup: String = "",
    val days: List<DayEntity> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: NetworkError? = null,
)
