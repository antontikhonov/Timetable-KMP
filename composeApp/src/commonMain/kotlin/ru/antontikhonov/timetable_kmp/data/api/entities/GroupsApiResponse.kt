package ru.antontikhonov.timetable_kmp.data.api.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupsApiResponse(

    @SerialName("groups")
    val groups: List<String>,
)
