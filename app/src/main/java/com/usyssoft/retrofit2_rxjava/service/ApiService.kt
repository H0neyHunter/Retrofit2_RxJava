package com.usyssoft.retrofit2_rxjava.service

import com.usyssoft.retrofit2_rxjava.model.CarList
import com.usyssoft.retrofit2_rxjava.util.Constants.Companion.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(EndPointsApi::class.java)

    fun getCarListData() : Single<List<CarList>>{
        return api.getCar()
    }
}