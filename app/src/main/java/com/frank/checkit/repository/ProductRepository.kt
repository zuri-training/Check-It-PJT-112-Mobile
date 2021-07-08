package com.frank.checkit.repository

import androidx.lifecycle.MutableLiveData
import com.frank.checkit.model.FakersData
import com.frank.checkit.network.ProductService
import com.frank.checkit.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ProductRepository {
    val getData = MutableLiveData<List<FakersData>>()

    fun loadData(): MutableLiveData<List<FakersData>>{

        //initiate the service
        val destinationService  = ServiceBuilder.buildService(ProductService::class.java)
        val requestCall =destinationService.getProductsData()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<FakersData>> {
            override fun onFailure(call: Call<List<FakersData>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<FakersData>>, response: Response<List<FakersData>>) {
                if (response.isSuccessful){
                    val data = response.body()!!
                    getData.value = data

                }
            }

        })

        return getData

    }


}