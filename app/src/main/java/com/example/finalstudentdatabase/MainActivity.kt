package com.example.finalstudentdatabase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonSave: Button
    private lateinit var fname: EditText
    private lateinit var lname: EditText
    private lateinit var dob: EditText
    private lateinit var fathername: EditText
    private lateinit var course: EditText
    private lateinit var csdate: EditText
    private lateinit var gender: EditText
    private lateinit var contact: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview_tasks)
        recyclerViewAdapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
        val divider = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getUserObserver().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })


        viewModel.fetchStudentRecords()

        buttonSave = findViewById(R.id.buttonSave)
        fname = findViewById(R.id.FirstName)
        lname = findViewById(R.id.LastName)
        dob = findViewById(R.id.Age)
        fathername = findViewById(R.id.FatherName)
        course = findViewById(R.id.Course)
        csdate = findViewById(R.id.CourseStart)
        gender = findViewById(R.id.gender)
        contact = findViewById(R.id.contact)

        buttonSave.setOnClickListener {
            val firstName = fname.text.toString().trim()
            val lastName = lname.text.toString().trim()
            val age = dob.text.toString().trim()
            val fatherName = fathername.text.toString().trim()
            val studentCourse = course.text.toString().trim()
            val courseStartDate = csdate.text.toString().trim()
            val studentGender = gender.text.toString().trim()
            val contactInfo = contact.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || fatherName.isEmpty() ||
                studentCourse.isEmpty() || courseStartDate.isEmpty() || studentGender.isEmpty() || contactInfo.isEmpty()) {
                Toast.makeText(this, "Please fill in all the columns", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (buttonSave.text == "Save Record") {
                val user = user(
                    0,
                    firstName,
                    lastName,
                    age,
                    fatherName,
                    studentCourse,
                    courseStartDate,
                    studentGender,
                    contactInfo
                )
                viewModel.insertUserinfo(user)
            } else if (buttonSave.text == "Update") {
                val userId = fname.getTag(R.id.FirstName).toString().toInt()
                val user = user(
                    userId,
                    firstName,
                    lastName,
                    age,
                    fatherName,
                    studentCourse,
                    courseStartDate,
                    studentGender,
                    contactInfo
                )
                viewModel.updateUserinfo(user)
                buttonSave.text = "Save Record"
            }

            clearFields()
        }
    }

    override fun onDeleteUserClickListener(user: user) {
        viewModel.deleteUserinfo(user)
    }

    override fun onItemClickListener(user: user) {
        buttonSave.text = "Update"
        fname.setTag(R.id.FirstName, user.id)
        fname.setText(user.firstName)
        lname.setText(user.lastName)
        dob.setText(user.dateOfBirth)
        fathername.setText(user.fatherName)
        course.setText(user.courseName)
        csdate.setText(user.courseStartDate)
        gender.setText(user.gender)
        contact.setText(user.contact)
    }

    private fun clearFields() {
        fname.setText("")
        lname.setText("")
        dob.setText("")
        fathername.setText("")
        course.setText("")
        csdate.setText("")
        gender.setText("")
        contact.setText("")
    }
}
