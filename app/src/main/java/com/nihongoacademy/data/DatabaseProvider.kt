package com.nihongoacademy.data

import android.content.Context
import androidx.room.Room

class DatabaseProvider(private val context: Context) {
    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "nihongo_academy.db"
        ).fallbackToDestructiveMigration().build()
    }
}
