package com.example.WeatherReport

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WeatherReportApplication : Application() {
    //伴生对象，在类中只能存在一个，相当于static类
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context
        const val TOKEN = "iM57w98W2ldcf0Ue"// 填入你申请到的令牌值
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}