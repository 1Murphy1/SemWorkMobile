package com.example.semwork

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.thecatapi.com/"
    private const val API_KEY = "live_D90RxD8BBtGWDcW1brQuHLmBOraEoUbs7kvSK1xxMrG5LDviZl55jCQYxxFWGWw1"

    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        val originalRequest: Request = chain.request()
        val newRequest: Request = originalRequest.newBuilder()
            .header("x-api-key", API_KEY)
            .build()

        Log.d("Request", "Request Details: ${newRequest.toString()}")

        chain.proceed(newRequest)
    }.build()
//создание в первый моммент 
    val api: CatApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()) //будет использоваться Gson
            .build()
            .create(CatApi::class.java)
    }
}
