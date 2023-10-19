package com.example.finalstudentdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class user(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")val id: Int = 0,
    @ColumnInfo(name="firstname")var firstName: String,
    @ColumnInfo(name="lastname")var lastName: String,
    @ColumnInfo(name="dateOfBirth")var dateOfBirth: String,
    @ColumnInfo(name="fatherName")var fatherName: String,
    @ColumnInfo(name="courseName")var courseName: String,
    @ColumnInfo(name="courseStartDate")var courseStartDate: String,
    @ColumnInfo(name="gender")var gender: String,
    @ColumnInfo(name="contact")var contact: String


)