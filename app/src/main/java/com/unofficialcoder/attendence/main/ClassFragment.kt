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
import com.unofficialcoder.attendence.model.ClassRoom


class ClassFragment : Fragment() {

    private lateinit var classRecyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var classListAdapter: ClassListAdapter


    private val clickListener: (String) -> Unit = {userName: String ->
        val fragment: Fragment =
            SubjectFragment()
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
        savedInstanceState: Bundle?): View? {

        return  inflater.inflate(R.layout.fragment_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        classRecyclerView = view.findViewById(R.id.class_list)

        classRecyclerView.layoutManager = LinearLayoutManager(context)
        classRecyclerView.setHasFixedSize(true)
        classListAdapter =
            ClassListAdapter( clickListener)

        var list: ArrayList<ClassRoom> = ArrayList()

        list.add(
            ClassRoom(
                "ClassName1",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )
        list.add(
            ClassRoom(
                "ClassName2",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )
        list.add(
            ClassRoom(
                "ClassName3",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )
        list.add(
            ClassRoom(
                "ClassName4",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )
        list.add(
            ClassRoom(
                "ClassName5",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )
        list.add(
            ClassRoom(
                "ClassName6",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )
        list.add(
            ClassRoom(
                "ClassName7",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )
        list.add(
            ClassRoom(
                "ClassName8",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )
        list.add(
            ClassRoom(
                "ClassName9",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )
        list.add(
            ClassRoom(
                "ClassName10",
                "BRANCH - CSE",
                "Teacher - Mr. Sharma"
            )
        )

        classRecyclerView.adapter = classListAdapter
        classListAdapter.updateList(list)
    }


}