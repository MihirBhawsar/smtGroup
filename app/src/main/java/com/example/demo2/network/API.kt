package com.example.demo2.network

import com.example.demo2.model.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("/post")
    fun getData(
        @Query("skip") skip: Int, @Query("limit") limit: Int, @Query("page") pageIndex: Int
    ): Call<MovieData>
}