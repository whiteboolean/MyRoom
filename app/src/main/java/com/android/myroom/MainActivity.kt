package com.android.myroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.TextView
import androidx.room.Room
import com.android.myroom.database.AppDatabase

/**
 *
 * Room在SQLite上提供一个抽象层，以便在充分利用SQLite的强大的功能的同时，能够流畅地访问数据库
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(applicationContext,
        AppDatabase::class.java,"database-name")
            .build()


        val all = db.userDao().getAll()

        val tv = findViewById<TextView>(R.id.tv)
        all.forEach {
            SystemClock.sleep(1000)
            tv.text = "${it.firstName},${it.lastName}"
        }



    }
}