package com.unofficialcoder.attendence.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unofficialcoder.attendence.R
import com.unofficialcoder.attendence.model.ClassRoom

class ClassListAdapter(
    private val clickLister: (String) -> Unit
    ) : RecyclerView.Adapter<ClassListAdapter.ClassViewHolder>(){

    private var classList: List<ClassRoom> = ArrayList<ClassRoom>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClassViewHolder =
        ClassViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.class_item,
                p0,
                false
            )
        )

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {

        val classRoom = classList.get(position)

        holder.tv_className.text = classRoom.className
        holder.tv_branchName.text = classRoom.branchName
        holder.tv_teacherName.text = classRoom.teacherName
//        val list = classList
//        if (list != null){
//            holder.bind(list[position])
//        }

        holder.tv_className.setOnClickListener(View.OnClickListener {
            clickLister(classRoom.className)
        })


    }

    override fun getItemCount() = 10

    fun updateList(list_of_Class: List<ClassRoom>){
        classList = list_of_Class
        notifyDataSetChanged()
    }


    class ClassViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){

         var tv_className: TextView
         var tv_branchName: TextView
         var tv_teacherName: TextView

        init{

            tv_className = itemView.findViewById<TextView>(R.id.class_name)
            tv_branchName = itemView.findViewById<TextView>(R.id.branch)
            tv_teacherName = itemView.findViewById<TextView>(R.id.teacher_name)

        }

    }
}