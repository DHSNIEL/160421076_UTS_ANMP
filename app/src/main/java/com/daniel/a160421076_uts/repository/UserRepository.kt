package com.daniel.a160421076_uts.repository

import com.daniel.a160421076_uts.model.User
import com.daniel.a160421076_uts.util.ApiClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {

    fun login(user: User, callback: (Boolean) -> Unit) {
        val call = ApiClient.service.login(user.username, user.password)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body() == "success") {
                    callback(true)
                } else {
                    callback(false)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback(false)
            }
        })
    }

    fun register(user: User, callback: (Boolean) -> Unit) {
        val call = ApiClient.service.register(
            user.username,
            user.namaDepan,
            user.namaBelakang,
            user.email,
            user.password,
            user.confPassword // Diperlukan ulang untuk konfirmasi password
        )
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body() == "success") {
                    callback(true)
                } else {
                    callback(false)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback(false)
            }
        })
    }
}