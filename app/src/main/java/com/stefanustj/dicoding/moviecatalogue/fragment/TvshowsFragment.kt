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
import com.stefanustj.dicoding.moviecatalogue.activity.DetailTvshowActivity
import com.stefanustj.dicoding.moviecatalogue.adapter.ListShowAdapter
import com.stefanustj.dicoding.moviecatalogue.model.TvShow
import com.stefanustj.dicoding.moviecatalogue.viewmodel.ShowViewModel
import kotlinx.android.synthetic.main.fragment_tvshows.*

class TvshowsFragment : Fragment(),LifecycleOwner {

    private lateinit var adapterShow: ListShowAdapter
    private lateinit var showViewModel: ShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler()

        adapterShow = ListShowAdapter()
        adapterShow.notifyDataSetChanged()

        tv_list.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        tv_list.adapter = adapterShow

        showViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ShowViewModel::class.java)

        handler.postDelayed({
            showViewModel.setShow()
            showLoading(true)
        },1000)

        showViewModel.getShow().observe(viewLifecycleOwner, Observer {showItems  ->
            if(showItems!=null){
                adapterShow.setData(showItems)
                showLoading(false)
            }
        })

        adapterShow.setOnItemClickCallback(object : ListShowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: TvShow) {
                showSelectedShow(data)
            }
        })
    }

    private fun showLoading(state: Boolean){
        if(state){
            pbShow.visibility = View.VISIBLE
        }
        else{
            pbShow.visibility = View.GONE
        }
    }

    private fun showSelectedShow(show: TvShow){
  //    Toast.makeText(context,context?.getString(R.string.chosen)+" ${show.name}", Toast.LENGTH_SHORT).show()
        val showData = TvShow(
            show.poster,
            show.name,
            show.overview,
            show.date
        )

        val goToDetail = Intent(activity,DetailTvshowActivity::class.java)
        goToDetail.putExtra(DetailTvshowActivity.EXTRA_SHOWS,showData)
        startActivity(goToDetail)
    }

}
