package com.daniel.a160421076_uts.model

import com.google.gson.annotations.SerializedName

data class Berita(
    @SerializedName("imageUrl")
    val gambar: String,
    @SerializedName("title")
    val judul: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("description")
    val deskripsi:String,
    @SerializedName("content")
    val konten:String
)
