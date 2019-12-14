package com.unofficialcoder.attendence.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.unofficialcoder.attendence.R
import com.unofficialcoder.attendence.model.attendence

class AttendenceSheetFragment : Fragment() {

    private lateinit var attendenceRecyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var attendenceListAdapter: AttendenceAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attendence_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attendenceRecyclerView = view.findViewById(R.id.attendence_sheet)

        attendenceRecyclerView.layoutManager = LinearLayoutManager(context)

        attendenceListAdapter = AttendenceAdapter()

        var list: ArrayList<attendence> = ArrayList()

        list.add(
            attendence("1", false)
        )
        list.add(
            attendence("2", false)
        )
        list.add(
            attendence("3", false)
        )
        list.add(
            attendence("4", false)
        )
        list.add(
            attendence("5", false)
        )
        list.add(
            attendence("6", false)
        )
        list.add(
            attendence("7", false)
        )
        list.add(
            attendence("8", false)
        )
        list.add(
            attendence("9", false)
        )
        list.add(
            attendence("10", false)
        )
        list.add(
            attendence("11", false)
        )
        list.add(
            attendence("12", false)
        )
        list.add(
            attendence("13", false)
        )
        list.add(
            attendence("14", false)
        )
        list.add(
            attendence("15", false)
        )
        list.add(
            attendence("16", false)
        )
        list.add(
            attendence("17", false)
        )

        attendenceRecyclerView.adapter = attendenceListAdapter
        attendenceListAdapter.updateList(list)

        val button = view.findViewById<Button>(R.id.bt_submit)
        button.setOnClickListener(View.OnClickListener {

            val fragment: Fragment =
                ClassFragment()
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            println(Toast.makeText(context, "Attendence Marked", Toast.LENGTH_SHORT).show())
        })


    }
}
