package ru.antontikhonov.timetable_kmp.domain.repositores

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.core.Result
import ru.antontikhonov.timetable_kmp.domain.entities.TimetableEntity

interface TimetableRepository {

    suspend fun getTimetable(groupNumber: String): Result<TimetableEntity, NetworkError>
}