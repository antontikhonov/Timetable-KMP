package ru.antontikhonov.timetable_kmp.timetable.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.core.Result
import ru.antontikhonov.timetable_kmp.core.data.safeCall
import ru.antontikhonov.timetable_kmp.timetable.data.api.entities.TimetableApiResponse

private const val BASE_URL = "https://antontikhonov.ru"

interface TimetableApiService {

    suspend fun getTimetable(groupNumber: String): Result<TimetableApiResponse, NetworkError>
}

class KtorTimetableApiService(
    private val httpClient: HttpClient,
) : TimetableApiService {

    override suspend fun getTimetable(groupNumber: String): Result<TimetableApiResponse, NetworkError> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/timetable/timetable/${groupNumber}"
            )
        }
    }
}
