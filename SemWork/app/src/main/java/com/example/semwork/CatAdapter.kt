package com.example.semwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Log

class CatAdapter(private val cats: List<Cat>) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.catImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = cats[position]
        Log.d("Cat Image", "URL: ${cat.url}")
        Glide.with(holder.imageView.context)
            .load(cat.url)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = cats.size
}