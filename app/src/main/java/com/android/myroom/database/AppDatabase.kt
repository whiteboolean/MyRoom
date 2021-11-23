package com.android.myroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.myroom.dao.UserDao
import com.android.myroom.entity.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao


    companion object {

        @JvmStatic
        @Volatile
        private var INSTANCE: AppDatabase? = null


        @JvmStatic
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "app_database"
                )
//                    .fallbackToDestructiveMigration() //强制升级 ,会清空数据中的数据
                    .addMigrations(MIGRATION_1_2())
                    .build()
                INSTANCE = instance
                return instance
            }
        }


        @JvmStatic
        fun MIGRATION_1_2(): Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER table Student add column sex CHAR")
            }

        }

    }


}