package ru.antontikhonov.timetable_kmp.timetable.data.mappers

import ru.antontikhonov.timetable_kmp.timetable.data.api.entities.TimetableDayApi
import ru.antontikhonov.timetable_kmp.timetable.data.api.entities.PairClassApi
import ru.antontikhonov.timetable_kmp.timetable.data.api.entities.TimetableApiResponse
import ru.antontikhonov.timetable_kmp.timetable.domain.DayEntity
import ru.antontikhonov.timetable_kmp.timetable.domain.PairClassEntity
import ru.antontikhonov.timetable_kmp.timetable.domain.TimetableEntity

fun TimetableApiResponse.toDomain(): TimetableEntity {
    return TimetableEntity(
        numberOfGroup = this.numberOfGroup,
        days = this.days.map { it.toDomain() },
    )
}

private fun TimetableDayApi.toDomain(): DayEntity {
    return DayEntity(
        day = this.day,
        classes = this.classes.map { it.toDomain() },
    )
}

private fun PairClassApi.toDomain(): PairClassEntity {
    return PairClassEntity(
        time = this.time,
        even = this.even,
        odd = this.odd,
    )
}