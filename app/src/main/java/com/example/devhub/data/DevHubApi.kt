package com.example.devhub.data

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DevHubApi(
    val context: Context
) {
    val resposta = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val DevHubService = resposta.create(DevHubRepository::class.java)
}