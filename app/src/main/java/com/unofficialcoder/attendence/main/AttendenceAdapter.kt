package com.unofficialcoder.attendence.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewDebug
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unofficialcoder.attendence.R
import com.unofficialcoder.attendence.model.attendence

class AttendenceAdapter() : RecyclerView.Adapter<AttendenceAdapter.AttendenceViewHolder>(){

    private var subList: List<attendence> = ArrayList<attendence>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AttendenceViewHolder =
        AttendenceViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.attendance_list,
                p0,
                false
            )
        )

    override fun onBindViewHolder(holder: AttendenceViewHolder, position: Int) {

        val sub = subList.get(position)

        holder.tv_subjetName.text =sub.rollno

        holder.checkBox.isChecked = false

        holder.checkBox.setOnClickListener(View.OnClickListener {
            holder.checkBox.isChecked = true
        })


    }

    override fun getItemCount() = 17

    fun updateList(list_of_Subject: List<attendence>){
        subList = list_of_Subject
        notifyDataSetChanged()
    }


    class AttendenceViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){

        var tv_subjetName: TextView
        var checkBox: CheckBox

        init{
            tv_subjetName = itemView.findViewById<TextView>(R.id.rollno)
            checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)
        }
    }
}