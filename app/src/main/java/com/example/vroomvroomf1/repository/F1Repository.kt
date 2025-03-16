package com.example.vroomvroomf1.data.repository

import com.example.vroomvroomf1.data.api.ErgastApiService
import com.example.vroomvroomf1.data.model.RaceResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class F1Repository {
    private val api: ErgastApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ergast.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ErgastApiService::class.java)
    }

    suspend fun getCurrentSeasonRaces(): RaceResponse {
        return api.getCurrentSeasonRaces()
    }
}
