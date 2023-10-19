package com.example.finalstudentdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface userDao {

    @Query("SELECT * FROM student_table ORDER By ID DESC")
    fun  getAllstudent():List<user>?

    @Insert
    fun insertUser(User:user)

    @Update
    fun updateUser(User:user)

    @Delete
    fun deleteUser(User:user)

}