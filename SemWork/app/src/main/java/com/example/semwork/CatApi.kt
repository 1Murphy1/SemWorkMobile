package com.example.semwork

import retrofit2.Call
import retrofit2.http.GET

interface CatApi {
    @GET("v1/images/search?limit=20")
    fun getCats(): Call<List<Cat>>
}