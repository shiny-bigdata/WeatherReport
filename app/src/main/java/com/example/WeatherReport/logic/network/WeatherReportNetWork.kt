package com.example.WeatherReport.logic.network


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object WeatherReportNetWork {
    private val placeService = ServiceCreator.create<PlaceService>()
    suspend fun searchPlaces(query:String) = placeService.searchPlaces(query).await()
   // 挂起函数
    //await是call的扩展函数，所有call类型的Retrofit网络请求接口都可以直接调用await函数了

    private suspend fun <T> Call<T>.await():T {
       //suspendCoroutine将当前协程挂起，在普通的线程中执行Lambda表达式
        return suspendCoroutine { continuation->
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        //调用resume恢复当前协程
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException(
                            RuntimeException("response body is null")
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }
}

