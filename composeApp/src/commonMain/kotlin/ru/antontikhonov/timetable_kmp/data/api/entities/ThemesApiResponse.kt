package ru.antontikhonov.timetable_kmp.data.api.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ThemesApiResponse(

    @SerialName("themes")
    val themes: List<ThemeApi>,
)

@Serializable
class ThemeApi(

    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("isDarkTheme")
    val isDarkTheme: Boolean,

    @SerialName("url")
    val url: String,
)
