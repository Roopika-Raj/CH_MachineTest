package com.example.ch_machinetest.sharedpreference

class APIError {

    val success: Boolean = false
    val message: String? = null

    fun success(): Boolean {
        return success
    }

    fun message(): String? {
        return message
    }
}