package com.frank.checkit.network

import com.frank.checkit.model.FakersData
import retrofit2.Call
import retrofit2.http.GET


interface ProductService {

    //@GET("products")
   // fun getProductsData(): Call<List<FakersData>>

    @GET("products")
    fun getProductsData():Call<List<FakersData>>

}