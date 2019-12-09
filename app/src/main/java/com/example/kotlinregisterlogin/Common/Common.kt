package com.example.kotlinregisterlogin.Common

import com.example.kotlinregisterlogin.Remote.IMyAPI
import com.example.kotlinregisterlogin.Remote.RetrofitClient

object Common {

    val BASE_URL = "http://49.247.134.78/edmtdb/"

    val api: IMyAPI
        get() = RetrofitClient.getClient(BASE_URL).create(IMyAPI::class.java)

}