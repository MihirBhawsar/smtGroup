package com.example.demo2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo2.databinding.MovieLayoutBinding
import com.example.demo2.model.Post


class AdapterMovies :
    RecyclerView.Adapter<AdapterMovies.ViewHolder>() {
    private var moviesModels: MutableList<Post> = ArrayList()
    fun setMovieList(movieList: MutableList<Post>) {
        this.moviesModels = movieList
        notifyDataSetChanged()
    }
    class ViewHolder(val binding: MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = moviesModels[position]
        holder.binding.textViewTitle.text = model.title
        holder.binding.textViewBody.text = model.body
        holder.binding.textViewViews.text = model.views.toString()
    }
    override fun getItemCount(): Int {
        return if (moviesModels.size > 0) moviesModels.size else 0
    }
}