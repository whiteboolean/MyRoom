package com.android.myroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.TextView
import androidx.room.Room
import com.android.myroom.database.AppDatabase
import com.android.myroom.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

/**
 *
 * Room在SQLite上提供一个抽象层，以便在充分利用SQLite的强大的功能的同时，能够流畅地访问数据库
 */
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.getDatabase(this)



        MainScope().launch(Dispatchers.Main) {
            insertData(db)
            val all = getAllUsers(db)
            val tv = findViewById<TextView>(R.id.tv)
            all.forEach {
                SystemClock.sleep(1000)
                Log.d(TAG, "onCreate: " + Thread.currentThread().name)
                tv.text = "${it.firstName},${it.lastName}"
                Log.d(TAG, "onCreate: " + it.lastName)
            }

        }

    }


    private suspend fun insertData(db: AppDatabase) {
        withContext(Dispatchers.IO) {
            val user1 = User(uid = 1, firstName = "Ray1", lastName = "Rookie1",sex = '男')
            val user2 = User(uid = 2, firstName = "Ray2", lastName = "Rookie2",sex = '男')
            val user3 = User(uid = 3, firstName = "Ray3", lastName = "Rookie3",sex = '男')
            val user4 = User(uid = 4, firstName = "Ray4", lastName = "Rookie4",sex = '男')
            val user5 = User(uid = 5, firstName = "Ray5", lastName = "Rookie5",sex = '男')
            db.userDao().insert(user1)
            db.userDao().insert(user2)
            db.userDao().insert(user3)
            db.userDao().insert(user4)
            db.userDao().insert(user5)
        }
    }


    private suspend fun getAllUsers(db: AppDatabase): List<User> {
        return withContext(Dispatchers.IO) {
            return@withContext db.userDao().getAll()
        }
    }
}