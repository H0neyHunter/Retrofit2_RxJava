package com.usyssoft.retrofit2_rxjava.model

import com.google.gson.annotations.SerializedName

data class CarList(
    @SerializedName("carmodel")
    var carModel: String?,
    @SerializedName("carbrand")
    var carBrand: String?,
    @SerializedName("carcolor")
    var carColor: String?,
    @SerializedName("carimage")
    var carImage: String?
)
