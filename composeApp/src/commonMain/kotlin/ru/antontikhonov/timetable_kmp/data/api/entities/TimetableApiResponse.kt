package ru.antontikhonov.timetable_kmp.data.api.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimetableApiResponse(

    @SerialName("number_of_group")
    val numberOfGroup: String,

    @SerialName("days")
    val days: List<TimetableDayApi>,
)

@Serializable
data class TimetableDayApi(

    @SerialName("day")
    val day: String,

    @SerialName("classes")
    val classes: List<PairClassApi>,
)

@Serializable
data class PairClassApi(

    @SerialName("time")
    val time: String,

    @SerialName("odd")
    val odd: String?,

    @SerialName("even")
    val even: String?,
)
