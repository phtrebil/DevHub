package com.example.devhub.data

import com.example.devhub.model.Dev
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DevHubRepository {

    @GET("users/{user}")
    fun buscaDev(@Path("user") user:String?): Call<Dev>
}