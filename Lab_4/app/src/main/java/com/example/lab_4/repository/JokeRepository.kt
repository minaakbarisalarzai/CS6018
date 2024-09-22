package com.example.lab_4.repository

import com.example.lab_4.data.ApiService
import com.example.lab_4.data.JokeDao
import com.example.lab_4.data.JokeEntity
import kotlinx.coroutines.flow.Flow

class JokeRepository(private val jokeDao: JokeDao, private val apiService: ApiService) {

    // Fetch jokes from Room DB
    fun getAllJokes(): Flow<List<JokeEntity>> {
        return jokeDao.getAllJokes()
    }

    // Insert a new joke into Room DB
    suspend fun insertJoke(joke: JokeEntity) {
        jokeDao.insertJoke(joke)
    }

    // Fetch a new joke from the API
    suspend fun getRandomJoke(): String {
        val response = apiService.getRandomJoke()
        return response.value
    }
}
