package com.example.ch_machinetest.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.ch_machinetest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    companion object {
        val baseURL: String = "https://dummyjson.com/"
        var retrofit: Retrofit? = null
        val client: Retrofit
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(baseURL)
                        .client(
                            OkHttpClient.Builder()
                                .addInterceptor(
                                    HttpLoggingInterceptor()
                                        .apply {
                                            level =
                                                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                                else HttpLoggingInterceptor.Level.NONE
                                        })
                                .build()
                        )
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit!!
            }
    }

    fun checkInternetConnection(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
}