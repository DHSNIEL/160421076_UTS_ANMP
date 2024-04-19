package com.daniel.a160421076_uts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniel.a160421076_uts.model.Berita
import com.daniel.a160421076_uts.util.RemoteService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {
    private val BASE_URL = "http://10.0.2.2/anmp/"

    private val beritaLD = MutableLiveData<List<Berita>>()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService = retrofit.create(RemoteService::class.java)

    fun refresh() {
        val call = apiService.getBerita()
        call.enqueue(object : Callback<List<Berita>> {
            override fun onResponse(call: Call<List<Berita>>, response: Response<List<Berita>>) {
                if (response.isSuccessful) {
                    beritaLD.value = response.body()
                } else {
                    beritaLD.value = emptyList()
                }
            }

            override fun onFailure(call: Call<List<Berita>>, t: Throwable) {
                 beritaLD.value = emptyList()
            }
        })
    }

    fun getBeritaLiveData(): MutableLiveData<List<Berita>> {
        return beritaLD
    }
}