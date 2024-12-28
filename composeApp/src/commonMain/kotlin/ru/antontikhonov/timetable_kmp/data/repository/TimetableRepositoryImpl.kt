package ru.antontikhonov.timetable_kmp.data.repository

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.core.Result
import ru.antontikhonov.timetable_kmp.core.map
import ru.antontikhonov.timetable_kmp.data.api.TimetableApiService
import ru.antontikhonov.timetable_kmp.data.mappers.toDomain
import ru.antontikhonov.timetable_kmp.domain.entities.TimetableEntity
import ru.antontikhonov.timetable_kmp.domain.repositores.TimetableRepository

class TimetableRepositoryImpl(
    private val apiService: TimetableApiService,
) : TimetableRepository {

    override suspend fun getTimetable(groupNumber: String): Result<TimetableEntity, NetworkError> {
        return apiService.getTimetable(groupNumber = groupNumber)
            .map { it.toDomain() }
    }
}