package com.example.WeatherReport.logic.network

import com.example.WeatherReport.WeatherReportApplication
import com.example.WeatherReport.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    /**
     * 搜索城市数据
     */
    @GET("v2/place?token=${WeatherReportApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}