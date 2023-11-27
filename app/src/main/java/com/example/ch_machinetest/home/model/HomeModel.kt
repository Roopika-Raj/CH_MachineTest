package com.example.ch_machinetest.home.model

data class HomeModel(
    val limit: Int,
    val products: ArrayList<Product>,
    val skip: Int,
    val total: Int
)