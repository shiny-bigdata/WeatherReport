package com.example.WeatherReport.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.WeatherReport.R
import kotlinx.android.synthetic.main.fragment_places.*


class PlaceFragment:Fragment() {
    //懒加载，按需加载
    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private lateinit var adpter: PlaceAdpter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        //gradle中引入了插件kotlin-android-extensions
        recycleView.layoutManager = layoutManager
        adpter = PlaceAdpter(this,viewModel.placeList)
        recycleView.adapter = adpter
        //输入查询的城市
        searchPlaceEdit.addTextChangedListener { editable ->
            val content = editable?.toString()
            if (content!!.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                recycleView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adpter.notifyDataSetChanged()
            }
        }
        //搜索出的城市数据
        viewModel.placeLiveData.observe(this, Observer { result ->
            val places = result.getOrNull()
            if (places != null) {
                recycleView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adpter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查到任何地点",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

    }
}