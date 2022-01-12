package com.example.WeatherReport.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object实现单例模式
object ServiceCreator {

        private const val BaseUrl = "https://api.caiyunapp.com/"
        private val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        fun <T> create(serviceClass: Class<T>):T = retrofit.create(serviceClass)
        /**
         * 内联函数，在编译时期,把调用这个函数的地方用这个函数的方法体进行替换
         * noinline,反内联函数
         * 泛型实化,,首先是内联函数,其次得加reified关键字,然后可以在函数内获取当前指定泛型的实际类型
         * 内联函数是替换到被调用的地方,所以不存在泛型擦除(泛型对于类型的约束只在编译期存在)问题
         */
        inline fun <reified T> create() : T = create(T::class.java)
}