package com.gl4.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class StudentListAdapter(private val students: ArrayList<Student>) : Filterable,
    RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    var dataFilterList: ArrayList<Student> = students


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val studentName: TextView = itemView.findViewById(R.id.student_name)
        val presence: CheckBox = itemView.findViewById(R.id.present)
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.isNullOrEmpty() || constraint == "all") {
                    dataFilterList = students
                } else if (constraint == "present") {
                    val resultList = ArrayList<Student>()
                    for (student in students) {
                        if (student.present) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList =resultList
                } else if (constraint == "absent") {
                    val resultList = ArrayList<Student>()
                    for (student in students) {
                        if (!student.present) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList =resultList

                }

                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                println(constraint.toString())
                (results?.values as ArrayList<Student> ).map { println(it.firstname) }
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }
        }
    }

    // Create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false) // Replace with your item layout
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataFilterList[position]
        holder.studentName.text = item.firstname + " " + item.lastname
        holder.presence.isChecked = item.present

        holder.presence.setOnClickListener {
            item.changePresence()
        }
    }


}