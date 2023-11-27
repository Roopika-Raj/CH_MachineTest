package com.example.ch_machinetest.api

import com.example.ch_machinetest.home.model.HomeModel
import com.example.ch_machinetest.productdetails.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CHApiInterface {

    @GET("products")
    fun getAll(

    ): Call<HomeModel>

    @GET("products/{id}")
    fun getProductDetails(
        @Path("id") id: Int,
    ): Call<ProductResponse>
}