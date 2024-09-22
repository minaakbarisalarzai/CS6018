package com.example.lab4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JokeDao {

    // Insert a joke into the database
    @Insert
    suspend fun insert(joke: JokeEntity)

    // Retrieve all jokes from the database as a List<JokeEntity>
    @Query("SELECT * FROM joke_table ORDER BY id DESC")
    suspend fun getAllJokes(): List<JokeEntity>  // Ensure this returns List<JokeEntity>
}

