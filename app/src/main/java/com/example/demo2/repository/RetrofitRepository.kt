package com.example.demo2.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.demo2.model.MovieData
import com.example.demo2.network.API
import com.example.demo2.network.ApiClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepository private constructor (app: Application) {

    private var instanceApi: API

    init {
        instanceApi= ApiClient.instance
    }

    companion object{
        private var retrofitRepository: RetrofitRepository?=null
        @Synchronized
        fun getInstance(app:Application): RetrofitRepository? {
            if (retrofitRepository == null) {
                retrofitRepository = RetrofitRepository(app)
            }
            return retrofitRepository
        }
    }

    fun loadPage(topMoviesResponse: MutableLiveData<MovieData>, page : Int) {
        instanceApi.getData(skip=0, limit = 20, pageIndex = page).enqueue(object : Callback<MovieData> {
            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                topMoviesResponse.value = null
            }

            override fun onResponse(
                call: Call<MovieData>,
                response: Response<MovieData>
            ) {

                if (response.isSuccessful){
                    topMoviesResponse.value = response.body()
                }else{
                    topMoviesResponse.value = null
                }
            }
        })
    }

}