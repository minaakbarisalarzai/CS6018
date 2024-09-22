package com.example.lab_4.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Data class for the joke response
data class JokeResponse(val value: String)

interface ApiService {
    @GET("jokes/random")
    suspend fun getRandomJoke(): JokeResponse

    companion object {
        private const val BASE_URL = "https://api.chucknorris.io/"

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
