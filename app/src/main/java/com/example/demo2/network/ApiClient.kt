package com.example.demo2.network

import android.content.Context
import android.util.Log
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val BASE_URL_API="https://dummyjson.com/"
    // Create an OkHttpClient with the Network Interceptor
    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor { chain ->
                // Add logging or modify request/response here
                val request = chain.request().newBuilder()
                    .build()
                val response = chain.proceed(request)

                // You can log or inspect the response here if needed
               Log.d("NetworkCall",response.toString())
                response
            }
            .build()
    }
    private fun retrofitService(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .baseUrl(BASE_URL_API)
            .build()
    }

    val instance: API by lazy {
        retrofitService().create(API::class.java)
    }
}