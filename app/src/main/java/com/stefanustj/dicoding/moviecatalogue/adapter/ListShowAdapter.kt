package com.stefanustj.dicoding.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stefanustj.dicoding.moviecatalogue.R
import com.stefanustj.dicoding.moviecatalogue.model.TvShow
import kotlinx.android.synthetic.main.item_row_tvshow.view.*

class ListShowAdapter() : RecyclerView.Adapter<ListShowAdapter.ListViewHolder>() {

    private val listShow: ArrayList<TvShow>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(items:ArrayList<TvShow>){
        listShow.clear()
        listShow.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_tvshow,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listShow[position])
    }

    override fun getItemCount(): Int = listShow.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow){
            with(itemView){
                Glide.with(this).load(tvShow.poster).into(poster_tv)
                name_tv.text = tvShow.name
                date_tv.text = tvShow.date
                overview_tv.text = tvShow.overview
                itemView.setOnClickListener {onItemClickCallback?.OnItemClicked(tvShow)}
            }
        }
    }

    interface OnItemClickCallback{
        fun OnItemClicked(data: TvShow)
    }
}