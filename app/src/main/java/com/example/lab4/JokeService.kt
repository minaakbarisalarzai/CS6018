package com.example.lab4

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Data class to hold the joke data
data class Joke(val value: String)

// Retrofit interface for the Chuck Norris API
interface JokeService {

    // Define an HTTP GET request to fetch a random joke
    @GET("jokes/random")
    suspend fun getRandomJoke(): Joke

    companion object {
        private var retrofitService: JokeService? = null

        // Singleton pattern to create and manage a single Retrofit instance
        fun getInstance(): JokeService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.chucknorris.io/") // Base URL for the API
                    .addConverterFactory(GsonConverterFactory.create()) // JSON converter
                    .build()
                retrofitService = retrofit.create(JokeService::class.java)
            }
            return retrofitService!!
        }
    }
}
