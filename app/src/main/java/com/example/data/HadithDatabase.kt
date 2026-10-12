package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HadithEntity::class], version = 1, exportSchema = false)
abstract class HadithDatabase : RoomDatabase() {
    abstract fun hadithDao(): HadithDao

    companion object {
        @Volatile
        private var INSTANCE: HadithDatabase? = null

        fun getInstance(context: Context): HadithDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HadithDatabase::class.java,
                    "ensiklopedia_hadits_sunnah.db"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
