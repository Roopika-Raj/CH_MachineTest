package com.example.ch_machinetest.productdetails.model

import com.google.gson.annotations.SerializedName

class ProductResponse {

    @SerializedName("brand")
    val barnd: String? = null

    @SerializedName("category")
    val category: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("discountPercentage")
    val discountPercentage: Double? = null

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("images")
    val images: List<String>? = null

    @SerializedName("price")
    val price: Int? = null

    @SerializedName("rating")
    val rating: Double? = null

    @SerializedName("stock")
    val stock: Int? = null

    @SerializedName("thumbnail")
    val thumbnail: String? = null

    @SerializedName("title")
    val title: String? = null
}