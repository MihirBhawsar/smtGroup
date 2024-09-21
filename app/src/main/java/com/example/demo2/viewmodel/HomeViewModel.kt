package com.example.demo2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demo2.model.MovieData
import com.example.demo2.repository.RetrofitRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG="MainActivity"
    private var retrofitRepository: RetrofitRepository? = RetrofitRepository.getInstance(application)
    private var _topMoviesFirstPageResponse = MutableLiveData<MovieData>()
    fun requestFirstPageTopMovies(page : Int) {
        Log.d(TAG,"requestFirstPageTopMovies")
        retrofitRepository!!.loadPage(_topMoviesFirstPageResponse , page)
    }
    val topMoviesFirstPageResponse : LiveData<MovieData>
        get() = _topMoviesFirstPageResponse

}