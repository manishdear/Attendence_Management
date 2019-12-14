package com.unofficialcoder.attendence.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.unofficialcoder.attendence.R
import com.unofficialcoder.attendence.ui.main.AttendenceSheetFragment
import com.unofficialcoder.attendence.ui.main.ClassFragment
import com.unofficialcoder.attendence.ui.main.SubjectFragment


class DashboardActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val headerView: View = navigationView.getHeaderView(0)
        val navUserName = headerView.findViewById<TextView>(R.id.username)
        navUserName.text = "Manish"
        val navEmail = headerView.findViewById<TextView>(R.id.emailid)
        navEmail.text = "Studmannish@gmail.com"
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        val node = toggle
        if(node != null){
            drawerLayout?.addDrawerListener(node)
        }

        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                ClassFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_message)
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_message ->
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    SubjectFragment()
            ).commit()
            R.id.nav_chat -> //startActivity(Intent(this, ClassActivity::class.java))

                supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                    ClassFragment()
            ).commit()
            R.id.nav_profile -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                AttendenceSheetFragment()
            ).commit()
            R.id.nav_share -> startActivity(Intent(this, DashboardActivity::class.java))
            R.id.nav_send -> Toast.makeText(this, "Dummy Report", Toast.LENGTH_SHORT).show()

        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}