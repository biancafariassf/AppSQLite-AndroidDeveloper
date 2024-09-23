package com.example.appdisney.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Disney::class], version = 1, exportSchema = false)
abstract class DisneyDatabase : RoomDatabase() {
    abstract fun disneyDao(): DisneyDao
}
