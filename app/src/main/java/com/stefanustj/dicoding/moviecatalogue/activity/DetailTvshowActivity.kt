package com.stefanustj.dicoding.moviecatalogue.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.stefanustj.dicoding.moviecatalogue.R
import com.stefanustj.dicoding.moviecatalogue.model.TvShow
import kotlinx.android.synthetic.main.activity_detail_tvshow.*
import kotlinx.android.synthetic.main.item_row_tvshow.*
import kotlinx.android.synthetic.main.item_row_tvshow.date_tv
import kotlinx.android.synthetic.main.item_row_tvshow.name_tv
import kotlinx.android.synthetic.main.item_row_tvshow.overview_tv
import kotlinx.android.synthetic.main.item_row_tvshow.poster_tv


class DetailTvshowActivity : AppCompatActivity() {

    companion object{
        var EXTRA_SHOWS = "extra_shows"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val result = intent.getParcelableExtra(EXTRA_SHOWS) as TvShow
        Glide.with(this@DetailTvshowActivity).load(result.poster).fitCenter().into(poster_tv)
        name_tv.text = result.name
        date_tv.text = result.date
        rating_tv.text = result.rating
        overview_tv.text = result.overview
    }
}
