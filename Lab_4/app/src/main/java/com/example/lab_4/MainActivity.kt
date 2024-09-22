package com.example.lab_4
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.lab_4.data.ApiService
import com.example.lab_4.data.JokeDatabase
import com.example.lab_4.repository.JokeRepository
import com.example.lab_4.viewmodel.JokeViewModel
import com.example.lab_4.viewmodel.JokeViewModelFactory


// MainActivity inherits from ComponentActivity to use the features of the modern Android API
// like lifecycle management and the new UI toolkit, Jetpack Compose.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Calling the superclass's onCreate to handle creation-related tasks

        // Retrieve the singleton instance of the database for the application context.
        // JokeDatabase is a singleton class that provides a static method to get the instance.
        val database = JokeDatabase.getDatabase(applicationContext)

        // Create an instance of ApiService to handle network operations.
        // ApiService contains a static method `create` that sets up Retrofit and returns an instance of ApiService.
        val apiService = ApiService.create()

        // Initialize a JokeRepository to manage data operations between the ViewModel and the data sources.
        // The repository requires a DAO (from Room) and an ApiService instance for database and network operations, respectively.
        val repository = JokeRepository(database.jokeDao(), apiService)

        // Create an instance of JokeViewModel to manage UI-related data in a lifecycle-conscious way.
        // ViewModelProvider is used to create ViewModel instances, ensuring they are scoped to the MainActivity lifecycle.
        // JokeViewModelFactory is passed to ViewModelProvider to instantiate JokeViewModel with the necessary dependencies.
        val jokeViewModel = ViewModelProvider(this, JokeViewModelFactory(repository))
            .get(JokeViewModel::class.java)

        // Set the content of the activity using Jetpack Compose.
        // setContent is a DSL provided by Jetpack Compose to define UI in a declarative manner.
        // MyApp is a Composable function that constructs the UI.
        setContent {
            MyApp(jokeViewModel)
        }
    }
}

//  ===========================================================================================================  //

// Annotating the function to be used with Jetpack Compose. Functions that generate UI are marked with @Composable.
// OptIn is used to acknowledge and accept the use of experimental APIs in Material3.
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyApp(jokeViewModel: JokeViewModel) {
    // Declaratively collecting state from the ViewModel. collectAsState() turns a Flow into a state object that Compose can react to.
    val jokes by jokeViewModel.jokes.collectAsState()
    val currentJoke by jokeViewModel.currentJoke.collectAsState()

    // Scaffold is a Material3 layout structure that provides basic material design layout structures like a top bar, floating action button, etc.
    Scaffold(
        topBar = {
            // TopAppBar is a composable that displays a material design app bar.
            TopAppBar(title = { Text("Chuck Norris Jokes") }) // Text composable displays a line of text with material style.
        },
        content = { padding ->
            // Column is a composable that places its children vertically.
            // Modifier is used to adjust the layout or to add behavior to composables.
            Column(
                modifier = Modifier
                    .fillMaxSize() // Modifier to make the Column fill the maximum size of its parent.
                    .padding(16.dp) // Adds padding inside the Column of 16 density-independent pixels on all sides.
                    .padding(padding) // Additional padding provided by the Scaffold's content padding.
            ) {
                // Button composable creates a clickable button.
                Button(
                    onClick = { jokeViewModel.fetchNewJoke() }, // Executes fetchNewJoke() from ViewModel when clicked.
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red // Sets the button's background color to red.
                    )
                ) {
                    Text("Get New Joke") // The text displayed within the button.
                }
                Spacer(modifier = Modifier.height(16.dp)) // Spacer adds vertical space between its siblings in the Column.
                Text(text = "Most Recent Joke:") // Static text label.
                Text(text = currentJoke, style = MaterialTheme.typography.titleLarge) // Displays the most recent joke with large title typography.
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    // LazyColumn displays its children as a scrollable list. It only composes and lays out the visible items.
                    items(jokes) { joke ->
                        Text(text = joke.joke) // Displays each joke.
                        Divider() // A thin line separating items in the list.
                    }
                }
            }
        }
    )
}

// =================================================== THE END ================================================== //
