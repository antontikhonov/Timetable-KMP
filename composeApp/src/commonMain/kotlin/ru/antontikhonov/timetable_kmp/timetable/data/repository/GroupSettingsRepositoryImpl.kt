package ru.antontikhonov.timetable_kmp.timetable.data.repository

import com.russhwolf.settings.Settings
import ru.antontikhonov.timetable_kmp.timetable.domain.GroupSettingsRepository

internal class GroupSettingsRepositoryImpl(
    private val settings: Settings,
) : GroupSettingsRepository {

    override fun getGroupSettings(): String {
        return settings.getString(key = GROUP_SETTINGS_KEY, defaultValue = "М23-504-1")
    }

    override fun putGroupSettings(value: String) {
        settings.putString(key = GROUP_SETTINGS_KEY, value = value)
    }

    private companion object {

        const val GROUP_SETTINGS_KEY = "GROUP_SETTINGS_KEY"
    }
}