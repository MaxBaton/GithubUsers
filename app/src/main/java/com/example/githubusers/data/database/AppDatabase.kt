package com.example.githubusers.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.githubusers.data.database.user.UserDao
import com.example.githubusers.data.database.user.UserDetailDao
import com.example.githubusers.data.models.UserData
import com.example.githubusers.data.models.UserDetailData

@Database(
    entities = [UserData::class, UserDetailData::class],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailDao(): UserDetailDao

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
                            .addMigrations(MIGRATION_1_2)
                            .build()
                    }
                }
            }

            return INSTANCE!!
        }

        private val MIGRATION_1_2 = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "create table UserDetailData(" +
                            "avatar_url VARCHAR(255)," +
                            "email VARCHAR(255)," +
                            "id INT primary key not null," +
                            "location VARCHAR(128)," +
                            "login VARCHAR(128)," +
                            "name VARCHAR(128)," +
                            "organizations_url VARCHAR(255)," +
                            "twitter_username VARCHAR(128)," +
                            "url VARCHAR(512)" +
                            ")"
                )
            }

        }
    }
}