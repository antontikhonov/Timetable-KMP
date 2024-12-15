package ru.antontikhonov.timetable_kmp.timetable.domain

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.core.Result

interface TimetableRepository {

    suspend fun getTimetable(groupNumber: String): Result<TimetableEntity, NetworkError>
}