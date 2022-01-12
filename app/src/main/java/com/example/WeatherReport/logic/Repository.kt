package com.example.WeatherReport.logic

import androidx.lifecycle.liveData
import com.example.WeatherReport.logic.model.Place
import com.example.WeatherReport.logic.model.PlaceResponse
import com.example.WeatherReport.logic.network.WeatherReportNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

object Repository {
    /**
     * 可以在liveData中任意使用挂起函数
     * liveData线程参数指定了Dispatchers.IO，网络请求代码放在了子线程中执行
     */
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        var result = try {
            val placeResponse = WeatherReportNetWork.searchPlaces(query)
               if (placeResponse.status=="ok") {
                   val places = placeResponse.places
                   Result.success(places)
               }else{
                   Result.failure(RuntimeException("response status is " +
                           "${placeResponse.status}"))
               }

        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}