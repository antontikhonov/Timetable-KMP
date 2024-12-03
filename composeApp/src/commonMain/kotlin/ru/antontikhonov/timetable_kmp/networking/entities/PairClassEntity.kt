package ru.antontikhonov.timetable_kmp.networking.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PairClassEntity(

    @SerialName("time")
    val time: String,

    @SerialName("odd")
    val odd: String?,

    @SerialName("even")
    val even: String?,
)
