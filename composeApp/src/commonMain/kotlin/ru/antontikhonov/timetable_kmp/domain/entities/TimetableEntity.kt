package ru.antontikhonov.timetable_kmp.domain.entities

data class TimetableEntity(
    val numberOfGroup: String,
    val days: List<DayEntity>,
)

data class DayEntity(
    val day: String,
    val classes: List<PairClassEntity>,
)

data class PairClassEntity(
    val time: String,
    val odd: String?,
    val even: String?,
)
