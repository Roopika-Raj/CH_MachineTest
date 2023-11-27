package com.example.ch_machinetest.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.ch_machinetest.R
import com.example.ch_machinetest.dashboard.view.DashboardActivity

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0, 0)
                finish()
            }, splashTimeOut
        )
    }
}