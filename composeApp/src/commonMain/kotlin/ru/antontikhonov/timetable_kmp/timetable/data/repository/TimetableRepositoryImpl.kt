package ru.antontikhonov.timetable_kmp.timetable.data.repository

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.core.Result
import ru.antontikhonov.timetable_kmp.core.map
import ru.antontikhonov.timetable_kmp.timetable.data.api.TimetableApiService
import ru.antontikhonov.timetable_kmp.timetable.data.mappers.toDomain
import ru.antontikhonov.timetable_kmp.timetable.domain.TimetableEntity
import ru.antontikhonov.timetable_kmp.timetable.domain.TimetableRepository

class TimetableRepositoryImpl(
    private val apiService: TimetableApiService,
) : TimetableRepository {

    override suspend fun getTimetable(groupNumber: String): Result<TimetableEntity, NetworkError> {
        return apiService.getTimetable(groupNumber = groupNumber)
            .map { it.toDomain() }
    }
}