package com.unofficialcoder.attendence.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.unofficialcoder.attendence.R
import com.unofficialcoder.attendence.model.Subject

class SubjectFragment : Fragment() {

    private lateinit var subjectRecyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var subjectListAdapter: SubjectListAdapter


    private val clickListener: (String) -> Unit = {userName: String ->
        val fragment: Fragment =
            AttendenceSheetFragment()
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        println(Toast.makeText(context, userName, Toast.LENGTH_SHORT).show())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjectRecyclerView = view.findViewById(R.id.subject_list)

        subjectRecyclerView.layoutManager = LinearLayoutManager(context)

        subjectListAdapter =
            SubjectListAdapter( clickListener)

        var list: ArrayList<Subject> = ArrayList()

        list.add(
            Subject(
                "Subject Name 1")

        )
        list.add(
            Subject(
                "Subject Name 2")

        )
        list.add(
            Subject(
                "Subject Name 3")

        )
        list.add(
            Subject(
                "Subject Name 4")

        )
        list.add(
            Subject(
                "Subject Name 5")

        )
        list.add(
            Subject(
                "Subject Name 6")

        )

        list.add(
            Subject(
                "Subject Name 7")

        )
        list.add(
            Subject(
                "Subject Name 8")

        )
        list.add(
            Subject(
                "Subject Name 9")

        )

        list.add(
            Subject(
                "Subject Name 10")
        )



        subjectRecyclerView.adapter = subjectListAdapter
        subjectListAdapter.updateList(list)


    }
}