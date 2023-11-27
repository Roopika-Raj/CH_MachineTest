package com.example.ch_machinetest.productdetails.presenter

import android.content.Context
import com.example.ch_machinetest.api.APIClient
import com.example.ch_machinetest.api.CHApiInterface
import com.example.ch_machinetest.productdetails.model.ProductResponse
import com.example.ch_machinetest.productdetails.view.ProductInterface
import com.example.ch_machinetest.sharedpreference.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductsPresenter {

    var productInterface: ProductInterface? = null
    private var context: Context? = null
    private var products: ProductResponse? = null
    private var sharedPreference: SharedPreference

    constructor(context: Context, productsDetailsInter: ProductInterface) {
        this.context = context
        this.productInterface = productsDetailsInter
        sharedPreference = SharedPreference(context)

    }


    fun getProductDetails(productId: Int) {

        val retrofit = APIClient.client
        val service = retrofit.create(CHApiInterface::class.java)
        val call = service.getProductDetails(productId)
        val unExpectedErrorMessage = "Unexpected error occurred"
        val noInternetMessage = "No internet connection"

        if (context == null) {
            productInterface?.productDetailsError(unExpectedErrorMessage)
        }
        if (!APIClient().checkInternetConnection(context)) {
            productInterface?.productDetailsError(noInternetMessage)
        }

        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {

                    products = response.body()

                    if (productId != null) {
                        productInterface?.productDetailsSuccess()
                        println("List Success")
                    } else {
                        productInterface?.productDetailsError(unExpectedErrorMessage)
                    }
                }

            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                productInterface?.productDetailsError("unexpected error occurred")
            }

        })
    }

    fun getDeatils(): ProductResponse? {
        return products
    }

    /* fun productDetails(productId: Int) {

         val retrofit = APIClient.client
         val service = retrofit.create(CHApiInterface::class.java)
         val call = service.getProductDetails(productId)
         val unExpectedErrorMessage = "Unexpected error occurred"
         val noInternetMessage = "No internet connection"

         if (context == null) {
             productInterface?.productDetailsError(unExpectedErrorMessage)
         }
         if (!APIClient().checkInternetConnection(context)) {
             productInterface?.productDetailsError(noInternetMessage)
         }

         call.enqueue(object : Callback<ProductResponse> {
             override fun onResponse(
                 call: Call<ProductResponse>,
                 response: Response<ProductResponse>
             ) {

                 if (response.isSuccessful) {
                     products = response.body()

                     if (products?.id != null) {
                         productInterface?.productDetailsSuccess()
                         println("List Success")
                     } else {
                         productInterface?.productDetailsError(unExpectedErrorMessage)
                     }
                 } else {
                     val error = ErrorUtils.parseError(response)
                     val message = error?.message
                     if (message != null) {
                         productInterface?.productDetailsError(message)
                     } else {
                         productInterface?.productDetailsError("Unexpected Error Occurred")
                     }
                 }
             }

             override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                 productInterface?.productDetailsError("Failed to load product details")
             }

         })
     }

     */


//    fun getDetails():ProductResponse{
//
//    }


}