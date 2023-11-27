package com.example.ch_machinetest.dashboard.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch_machinetest.R
import com.example.ch_machinetest.home.view.HomeContainerFragment
import com.example.ch_machinetest.sharedpreference.SharedPreference

class DashboardActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val homeContainerFragment = HomeContainerFragment()
    private lateinit var sharedPreference: SharedPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        sharedPreference = SharedPreference(applicationContext)
        fragmentManager.beginTransaction().apply {
            add(
                R.id.dashBoardFrame,
                homeContainerFragment,
                resources.getString(R.string.fragmentHome)
            )

        }.commit()

    }
}