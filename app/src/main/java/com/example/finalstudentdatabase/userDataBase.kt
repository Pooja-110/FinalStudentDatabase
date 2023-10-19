package com.example.finalstudentdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [user::class], version = 1)
abstract class userDataBase : RoomDatabase()
{

    abstract fun userDao():userDao?
    companion object
    {

        private var INSTANCE:userDataBase?=null
        fun getAppDatabase(context: Context):userDataBase?
        {
            if(INSTANCE==null)
            {
                INSTANCE = Room.databaseBuilder<userDataBase>(
                    context.applicationContext,
                    userDataBase::class.java, "student_table"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }

}