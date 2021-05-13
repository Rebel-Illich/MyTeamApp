package com.hunter.myteams.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private const val BASE_URL = "https://mocki.io"

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .build()

    private fun retrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(createGSon()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun createGSon(): Gson {
        return GsonBuilder()
            .create()
    }

    fun getRestApi(): ApiRequest{
        return retrofitService().create(ApiRequest::class.java)
    }

}