package com.daniel.a160421076_uts.util

import com.daniel.a160421076_uts.model.Berita
import com.daniel.a160421076_uts.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.GET

interface Service{
    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ):Call<String>

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") username: String,
        @Field("namaDepan") namaDepan: String,
        @Field("namaBelakang") namaBelakang: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confPassword") confPassword: String,
    ):Call<String>

    @GET("data.json")
    fun getBerita(): Call<List<Berita>>

    @FormUrlEncoded
    @POST("get.php")
    fun getUserData(
        @Field("username") username: String
    ): Call<User>


    @FormUrlEncoded
    @POST("update.php")
    fun update(
        @Field("username") username: String,
        @Field("namaDepan") namaDepan: String,
        @Field("namaBelakang") namaBelakang: String,
        @Field("password") password: String
    ): Call<String>
}