package ru.antontikhonov.timetable_kmp.data.repository

import com.russhwolf.settings.ObservableSettings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import ru.antontikhonov.timetable_kmp.domain.repositores.ThemeSettingsRepository

internal class ThemeSettingsRepositoryImpl(
    private val settings: ObservableSettings,
) : ThemeSettingsRepository {

    override fun getThemeSettings(): Flow<String?> {
        return callbackFlow {
            val initialValue = settings.getStringOrNull(THEME_SETTINGS_KEY)
            trySend(initialValue)

            val listener = settings.addStringOrNullListener(THEME_SETTINGS_KEY) { value ->
                trySend(value)
            }
            awaitClose {
                listener.deactivate()
            }
        }
    }

    override fun putThemeSettings(value: String) {
        settings.putString(key = THEME_SETTINGS_KEY, value = value)
    }

    private companion object {

        const val THEME_SETTINGS_KEY = "THEME_SETTINGS_KEY"
    }
}
