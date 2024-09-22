package com.example.lab_4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_4.data.JokeEntity
import com.example.lab_4.repository.JokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JokeViewModel(private val repository: JokeRepository) : ViewModel() {

    // Flow to track the list of jokes
    private val _jokes = MutableStateFlow<List<JokeEntity>>(emptyList())
    val jokes = _jokes.asStateFlow()

    // Flow to track the current joke
    private val _currentJoke = MutableStateFlow("")
    val currentJoke = _currentJoke.asStateFlow()

    init {
        getJokes()
    }

    // Fetch all jokes from the repository
    fun getJokes() {
        viewModelScope.launch {
            repository.getAllJokes().collect { jokeList ->
                _jokes.value = jokeList
            }
        }
    }

    // Fetch a new joke from the API and insert it into the Room database
    fun fetchNewJoke() {
        viewModelScope.launch {
            val jokeText = repository.getRandomJoke()
            repository.insertJoke(JokeEntity(joke = jokeText))
            _currentJoke.value = jokeText
        }
    }
}
