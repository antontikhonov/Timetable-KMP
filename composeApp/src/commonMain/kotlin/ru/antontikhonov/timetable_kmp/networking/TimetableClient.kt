package ru.antontikhonov.timetable_kmp.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import ru.antontikhonov.timetable_kmp.networking.entities.TimetableEntity
import ru.antontikhonov.timetable_kmp.util.NetworkError
import ru.antontikhonov.timetable_kmp.util.Result

class TimetableClient(
    private val httpClient: HttpClient,
) {

    suspend fun getTimetable(groupNumber: String = "М23-504-1"): Result<TimetableEntity, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://antontikhonov.ru/timetable/timetable/${groupNumber}"
            )

        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val timetableEntity = response.body<TimetableEntity>()
                Result.Success(timetableEntity)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}
