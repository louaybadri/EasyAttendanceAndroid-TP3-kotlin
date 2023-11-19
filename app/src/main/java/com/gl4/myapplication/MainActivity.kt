package com.gl4.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gl4.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // inflate the layout
        setContentView(binding.root) // set the content view
        val student1 = Student("Louay", "Badri", true)
        val student2 = Student("Aziz", "Bellaaj", false)
        val student3 = Student("Salma", "Ghabri", true)
        val student4 = Student("Safa", "Ouesleti", false)
        val students = arrayListOf<Student>(student1, student2, student3, student4)
        val studentAdapter = StudentListAdapter(students)
        binding.list.apply {
            adapter =  studentAdapter// set the adapter
            layoutManager = LinearLayoutManager(this@MainActivity) // set the layout manager
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.all -> studentAdapter.filter.filter("all")
                R.id.present -> studentAdapter.filter.filter("present")
                R.id.absent -> studentAdapter.filter.filter("absent")
            }
            }



    }






}