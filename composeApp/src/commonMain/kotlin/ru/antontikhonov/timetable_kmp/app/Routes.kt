package ru.antontikhonov.timetable_kmp.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object TimetableGraph: Route

    @Serializable
    data object Timetable: Route

    @Serializable
    data object MainSettings: Route

    @Serializable
    data object GroupSettings: Route
}
