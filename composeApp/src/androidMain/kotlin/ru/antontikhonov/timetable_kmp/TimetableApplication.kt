package ru.antontikhonov.timetable_kmp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import ru.antontikhonov.timetable_kmp.di.initKoin

class TimetableApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@TimetableApplication)
        }
    }
}
