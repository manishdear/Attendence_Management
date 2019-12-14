package com.unofficialcoder.attendence.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unofficialcoder.attendence.R
import com.unofficialcoder.attendence.model.Subject

class SubjectListAdapter(
    private val clickLister: (String) -> Unit
) : RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder>(){

    private var subList: List<Subject> = ArrayList<Subject>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SubjectViewHolder =
        SubjectViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.subject_item,
                p0,
                false
            )
        )

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {

        val sub = subList.get(position)

        holder.tv_subjetName.text = sub.subName

        holder.tv_subjetName.setOnClickListener(View.OnClickListener {
            clickLister(sub.subName)
        })


    }

    override fun getItemCount() = 10

    fun updateList(list_of_Subject: List<Subject>){
        subList = list_of_Subject
        notifyDataSetChanged()
    }


    class SubjectViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView),
        View.OnClickListener{

        var tv_subjetName: TextView

        init{
            tv_subjetName = itemView.findViewById<TextView>(R.id.sub_name)
        }

        override fun onClick(v: View?) {

        }
    }
}