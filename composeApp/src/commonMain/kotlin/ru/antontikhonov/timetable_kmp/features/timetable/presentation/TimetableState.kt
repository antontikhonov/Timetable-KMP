package ru.antontikhonov.timetable_kmp.features.timetable.presentation

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.domain.entities.DayEntity

data class TimetableState(
    val numberOfGroup: String = "",
    val currentDate: String = "",
    val currentMonthIndex: Int = 0,
    val days: List<DayEntity> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: NetworkError? = null,
)
