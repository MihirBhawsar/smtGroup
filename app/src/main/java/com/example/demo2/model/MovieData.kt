package com.example.demo2.model

data class MovieData(
    val limit: Int,
    val posts: List<Post>,
    val skip: Int,
    val total: Int
)