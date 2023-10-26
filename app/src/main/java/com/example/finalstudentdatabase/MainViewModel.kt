package com.example.finalstudentdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(app: Application): AndroidViewModel(app) {
    private val allUser: MutableLiveData<List<user>?> = MutableLiveData()

    fun getUserObserver(): MutableLiveData<List<user>?> {
        return allUser
    }

    fun getAllUsers() {
        val userDao = userDataBase.getAppDatabase(getApplication())?.userDao()
        val list = userDao?.getAllstudent()
        allUser.postValue(list)
    }
    fun insertUserinfo(entity: user) {
        val userDao = userDataBase.getAppDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
        getAllUsers()
    }

    fun updateUserinfo(entity: user) {
        val userDao = userDataBase.getAppDatabase(getApplication())?.userDao()
        userDao?.updateUser(entity)
        getAllUsers()
    }

    fun deleteUserinfo(entity: user) {
        val userDao = userDataBase.getAppDatabase(getApplication())?.userDao()
        userDao?.deleteUser(entity)
        getAllUsers()
    }

    fun fetchStudentRecords() {
        getAllUsers()
    }

}
