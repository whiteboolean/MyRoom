package com.android.myroom.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.myroom.entity.User

@Dao /* data access */
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll():List<User>

    @Query("select * from user where uid in (:userIds)")
    suspend fun loadAllByIds(userIds:IntArray) :List<User>

    @Query("select * from user where first_name like :first and last_name like :last limit 1")
    suspend fun findByName(first:String ,last:String ) :User

    @Insert
    suspend fun insertAll(vararg users:User)

    @Insert
    suspend fun insert(user:User)

    @Delete
    suspend fun delete(user:User)
}