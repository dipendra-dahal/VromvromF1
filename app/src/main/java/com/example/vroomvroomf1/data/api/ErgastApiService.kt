package com.example.vroomvroomf1.data.api

import com.example.vroomvroomf1.data.model.RaceResponse
import retrofit2.http.GET

interface ErgastApiService {
    @GET("api/f1/current.json")
    suspend fun getCurrentSeasonRaces(): RaceResponse
}
