package com.example.semwork

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        RetrofitInstance.api.getCats().enqueue(object : Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                if (response.isSuccessful) {
                    response.body()?.let { cats ->
                        Log.d("API Response", "Cats: $cats")
                        recyclerView.adapter = CatAdapter(cats)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load cats: ${response.message()}", Toast.LENGTH_SHORT).show()
                    Log.e("API Error", "Failed response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("API Error", "Error: ${t.message}")
            }
        })
    }
}