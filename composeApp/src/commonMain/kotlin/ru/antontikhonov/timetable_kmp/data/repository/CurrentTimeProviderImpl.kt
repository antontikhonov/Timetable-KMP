package ru.antontikhonov.timetable_kmp.data.repository

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import ru.antontikhonov.timetable_kmp.domain.repositores.CurrentTimeProvider

class CurrentTimeProviderImpl : CurrentTimeProvider {

    override fun invoke(): LocalDate {
        val now = Clock.System.now()
        return now.toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}