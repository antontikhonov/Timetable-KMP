package ru.antontikhonov.timetable_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform