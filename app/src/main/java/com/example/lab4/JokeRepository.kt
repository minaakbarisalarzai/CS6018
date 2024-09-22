package com.example.lab4


// Repository class to manage fetching jokes from the API and saving them to the database
class JokeRepository(private val jokeService: JokeService, private val jokeDao: JokeDao) {

    // Fetch a random joke from the Chuck Norris API
    suspend fun fetchRandomJoke(): String? {
        return try {
            val response = jokeService.getRandomJoke() // Call the API to get a joke
            response.value // Return the joke text
        } catch (e: Exception) {
            // Handle the case where there's an error (e.g., network error)
            null
        }
    }

    // Save a joke to the Room database
    suspend fun saveJoke(joke: String) {
        jokeDao.insert(JokeEntity(joke = joke)) // Save the joke in the database
    }

    // Retrieve all saved jokes from the Room database
    suspend fun getAllJokes(): List<String> {
        return jokeDao.getAllJokes().map { it.joke } // Get the jokes and convert them to a list of strings
    }
}
