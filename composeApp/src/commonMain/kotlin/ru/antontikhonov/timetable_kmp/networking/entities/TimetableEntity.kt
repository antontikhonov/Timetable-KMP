package ru.antontikhonov.timetable_kmp.networking.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimetableEntity(

    @SerialName("number_of_group")
    val numberOfGroup: String,

    @SerialName("days")
    val days: List<DayEntity>,
)
