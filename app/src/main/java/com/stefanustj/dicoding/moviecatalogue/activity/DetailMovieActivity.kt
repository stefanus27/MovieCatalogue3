package com.stefanustj.dicoding.moviecatalogue.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.stefanustj.dicoding.moviecatalogue.R
import com.stefanustj.dicoding.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.activity_detail_movie.*


class DetailMovieActivity : AppCompatActivity() {

    companion object{
        var EXTRA_MOVIES = "extra_movies"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val result = intent.getParcelableExtra(EXTRA_MOVIES) as Movie
        Glide.with(this@DetailMovieActivity).load(result.poster).fitCenter().into(poster_mv)
        name_mv.text = result.name
        date_mv.text = result.date
        rating_mv.text = result.rating
        overview_mv.text = result.overview
    }
}
