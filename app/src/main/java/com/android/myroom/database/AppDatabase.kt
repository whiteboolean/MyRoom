package com.android.myroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.myroom.dao.UserDao
import com.android.myroom.entity.User

@Database(entities = [User::class],version =  1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun userDao():UserDao
}