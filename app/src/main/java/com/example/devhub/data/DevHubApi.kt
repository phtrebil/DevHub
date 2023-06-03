package com.example.devhub.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DevHubApi(

) {
    val resposta = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val devHubService = resposta.create(DevHubRepository::class.java)
}

