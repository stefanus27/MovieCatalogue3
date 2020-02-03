package com.stefanustj.dicoding.moviecatalogue.fragment


import android.content.Intent
import android.os.Bundle
import android.os.Handler

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.stefanustj.dicoding.moviecatalogue.R
import com.stefanustj.dicoding.moviecatalogue.activity.DetailMovieActivity
import com.stefanustj.dicoding.moviecatalogue.adapter.ListMovieAdapter
import com.stefanustj.dicoding.moviecatalogue.model.Movie
import com.stefanustj.dicoding.moviecatalogue.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment(),LifecycleOwner {

    private lateinit var adapter: ListMovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler()

        adapter = ListMovieAdapter()
        adapter.notifyDataSetChanged()

        mv_list.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mv_list.adapter = adapter

        movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)

        handler.postDelayed({
            movieViewModel.setMovie()
            showLoading(true)
        },1000)

        movieViewModel.getMovie().observe(viewLifecycleOwner, Observer {movieItems  ->
            if(movieItems!=null){
                adapter.setData(movieItems)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movie) {
                showSelectedMovie(data)
            }
        })
    }

    private fun showLoading(state: Boolean){
        if(state){
            pbMovie.visibility = View.VISIBLE
        }
        else{
            pbMovie.visibility = View.GONE
        }
    }


    private fun showSelectedMovie(show: Movie){
    //  Toast.makeText(context,context?.getString(R.string.chosen)+" ${show.name}", Toast.LENGTH_SHORT).show()
        val showData = Movie(
            show.poster,
            show.name,
            show.overview,
            show.date
        )

        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.EXTRA_MOVIES,showData)
        startActivity(goToDetail)
    }
}
