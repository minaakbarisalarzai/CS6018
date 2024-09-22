package com.example.lab4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf

class JokeViewModel(private val repository: JokeRepository) : ViewModel() {

    // List to hold all the jokes
    val jokeList = mutableStateListOf<String>()
    var latestJoke = ""

    // Fetch a random joke and add it to the list
    fun getJoke() {
        viewModelScope.launch {
            val joke = repository.fetchRandomJoke()
            joke?.let {
                latestJoke = it
                jokeList.add(it)
                repository.saveJoke(it) // Save the joke in the database
            }
        }
    }

    // Load all jokes from the Room database
    fun loadStoredJokes() {
        viewModelScope.launch {
            val jokes = repository.getAllJokes()
            jokeList.clear()
            jokeList.addAll(jokes)
        }
    }
}
