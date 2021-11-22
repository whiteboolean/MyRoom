package com.android.myroom.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.myroom.entity.User

@Dao /* data access */
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll():List<User>

    @Query("select * from user where uid in (:userIds)")
    fun loadAllByIds(userIds:IntArray) :List<User>

    @Query("select * from user where first_name like :first and last_name like :last limit 1")
    fun findByName(first:String ,last:String ) :User

    @Insert
    fun insertAll(vararg users:User)

    @Delete
    fun delete(user:User)
}