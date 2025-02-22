package ru.antontikhonov.timetable_kmp.domain.repositores

import kotlinx.datetime.LocalDate

interface CurrentTimeProvider {

    operator fun invoke(): LocalDate
}