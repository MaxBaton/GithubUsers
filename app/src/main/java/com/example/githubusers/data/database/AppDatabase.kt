package com.example.githubusers.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubusers.data.database.user.UserDao
import com.example.githubusers.data.models.UserData

@Database(
    entities = [UserData::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "GithubUsersDb"

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DB_NAME
                        )
                            .build()
                    }
                }
            }

            return INSTANCE!!
        }
    }
}