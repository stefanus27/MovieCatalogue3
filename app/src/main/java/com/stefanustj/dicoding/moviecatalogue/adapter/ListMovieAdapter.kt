package com.stefanustj.dicoding.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stefanustj.dicoding.moviecatalogue.R
import com.stefanustj.dicoding.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.item_row_movie.view.*

class ListMovieAdapter() : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {

    private val listMovie: ArrayList<Movie>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(items:ArrayList<Movie>){
        listMovie.clear()
        listMovie.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie){
            with(itemView){
                Glide.with(this).load(movie.poster).into(poster_mv)
                name_mv.text = movie.name
                date_mv.text = movie.date
                overview_mv.text = movie.overview
                itemView.setOnClickListener {onItemClickCallback?.OnItemClicked(movie)}
            }
        }
    }

    interface OnItemClickCallback{
        fun OnItemClicked(data: Movie)
    }
}