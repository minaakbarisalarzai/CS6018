package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Room database
        val database = JokeDatabase.getDatabase(this)
        val jokeDao = database.jokeDao()
        val jokeService = JokeService.getInstance()
        val jokeRepository = JokeRepository(jokeService, jokeDao)

        setContent {
            JokeApp(viewModel = JokeViewModel(jokeRepository))
        }
    }
}

@Composable
fun JokeApp(viewModel: JokeViewModel = viewModel()) {
    var latestJoke by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadStoredJokes() // Load jokes from the database when the app starts
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Button to fetch a random joke
        Button(
            onClick = {
                viewModel.getJoke()
                latestJoke = viewModel.latestJoke
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Get Random Joke")
        }

        // Display the latest joke
        Text(text = "Latest Joke: $latestJoke", modifier = Modifier.padding(8.dp))

        // Lazy list to show all saved jokes
        LazyColumn {
            items(viewModel.jokeList) { joke ->
                Text(text = joke, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
