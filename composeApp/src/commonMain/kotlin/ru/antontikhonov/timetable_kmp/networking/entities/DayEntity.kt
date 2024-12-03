package ru.antontikhonov.timetable_kmp.networking.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayEntity(

    @SerialName("day")
    val day: String,

    @SerialName("classes")
    val classes: List<PairClassEntity>,
)
