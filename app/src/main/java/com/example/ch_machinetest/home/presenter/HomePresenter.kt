package com.example.ch_machinetest.home.presenter

import android.content.Context
import com.example.ch_machinetest.api.APIClient
import com.example.ch_machinetest.api.CHApiInterface
import com.example.ch_machinetest.home.model.HomeModel
import com.example.ch_machinetest.home.model.Product
import com.example.ch_machinetest.home.view.IHomeFragment
import com.example.ch_machinetest.sharedpreference.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter {

    private var homeInterface: IHomeFragment? = null
    private var contxt: Context? = null
    private var category: HomeModel? = null
    private var sharedPreference: SharedPreference

    constructor(context: Context, homeInter: IHomeFragment) {
        this.homeInterface = homeInter
        this.contxt = context
        sharedPreference = SharedPreference(context)
    }

    fun allProducts() {

        val retrofit = APIClient.client
        val service = retrofit.create(CHApiInterface::class.java)
        val call = service.getAll()
        val unExpectedErrorMessage = "Unexpected error occurred"
        val noInternetMessage = "No internet connection"

        if (contxt == null) {
            homeInterface?.getAllProductsFailure(unExpectedErrorMessage)
        }
        if (!APIClient().checkInternetConnection(contxt)) {
            homeInterface?.getAllProductsFailure(noInternetMessage)
        }

        call.enqueue(object : Callback<HomeModel> {
            override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
                if (response.isSuccessful) {
                    category = response.body()

                    if (category?.products?.isEmpty() == true) {
                        homeInterface?.getAllProductsFailure("Failure")
                    } else {
                        homeInterface?.getAllProductsSuccess()
                        println("All product success")
                    }
                }

            }

            override fun onFailure(call: Call<HomeModel>, t: Throwable) {
                homeInterface?.getAllProductsFailure("unexpected error occurred")
            }

        })
    }

    fun getAllCategories(): ArrayList<Product>? {
        if (category != null) {
            return category!!.products
        } else {
            return null
        }
    }

}


