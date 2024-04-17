package com.daniel.a160421076_uts.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient{
    private const val BASE_URL = "http://10.0.2.2/anmp/"

    val gson : Gson = GsonBuilder()
        .setLenient()
        .create()


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val service : RemoteService = retrofit.create(RemoteService::class.java)
}