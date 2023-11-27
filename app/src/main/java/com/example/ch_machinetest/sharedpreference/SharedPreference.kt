package com.example.ch_machinetest.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPreference(context: Context) {
    private val PREFS_NAME = "CODINGHANDS"
    val sharedPref: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    companion object {

    }


}