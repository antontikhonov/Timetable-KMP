package ru.antontikhonov.timetable_kmp.domain.repositores

interface GroupSettingsRepository {

    fun getGroupSettings(): String

    fun putGroupSettings(value: String)
}
