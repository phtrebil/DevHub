package com.example.devhub.model

import com.google.gson.annotations.SerializedName

class Dev(
    @SerializedName("avatar_url")
    val imagem: String,
    @SerializedName("name")
    val nome: String,
    @SerializedName("login")
    val usuario: String,
    @SerializedName("bio")
    val sobre: String
) {
}