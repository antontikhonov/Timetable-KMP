package ru.antontikhonov.timetable_kmp.timetable.domain

interface GroupSettingsRepository {

    fun getGroupSettings(): String

    fun putGroupSettings(value: String)
}
