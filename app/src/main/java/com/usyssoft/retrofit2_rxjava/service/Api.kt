package com.usyssoft.retrofit2_rxjava.service

import com.usyssoft.retrofit2_rxjava.model.CarList
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET("H0neyHunter/Retrofit2_RxJava/master/sample.json")
    fun getCar() : Single<List<CarList>>
}