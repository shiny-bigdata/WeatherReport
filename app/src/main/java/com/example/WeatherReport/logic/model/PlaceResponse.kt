package com.example.WeatherReport.logic.model

import com.google.gson.annotations.SerializedName

    //data类中自动生成equal(),hashCode(),toString()等方法
    //主构造函数至少含有一个参数，并用var/val修饰
    //数据类型不能是抽象、密闭、开发和内部的
    data class PlaceResponse(val status:String,val places:List<Place>)
    data class Place(val name:String,val location:Location,
                     //serializedName:命名规范不一致，让JSON和kotlin之间形成映射
                     @SerializedName("formatted_address") val address:String)
    //经纬度
    data class Location(val lng:String,val lat:String)
