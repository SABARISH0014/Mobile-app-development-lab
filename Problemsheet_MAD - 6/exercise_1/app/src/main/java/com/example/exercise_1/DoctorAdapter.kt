package com.example.exercise_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(
    private val fullList: List<Doctor>,
    private val onCallClick: (String) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    private var displayList = fullList.toList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvDoctorName)
        val tvDept: TextView = view.findViewById(R.id.tvDepartment)
        val btnCall: Button = view.findViewById(R.id.btnCall)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = displayList[position]
        holder.tvName.text = doctor.name
        holder.tvDept.text = doctor.department
        holder.btnCall.setOnClickListener { onCallClick(doctor.phoneNumber) }
    }

    override fun getItemCount() = displayList.size

    fun filter(query: String) {
        displayList = if (query.isEmpty()) {
            fullList
        } else {
            fullList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.department.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}