package com.practice.recipesapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): Dao
}