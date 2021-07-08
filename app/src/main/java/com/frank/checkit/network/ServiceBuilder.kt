package com.frank.checkit.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL ="https://fakestoreapi.com/"


    // create http client
    private val okHttp = OkHttpClient.Builder()
            .addInterceptor(addLog())

    //retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())



    //create retrofit Instance
    private val retrofit = builder.build()




    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }



    fun addLog(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}