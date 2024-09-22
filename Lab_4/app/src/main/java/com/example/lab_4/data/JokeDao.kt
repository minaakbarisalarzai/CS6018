package com.example.lab_4.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {
    @Insert
    suspend fun insertJoke(joke: JokeEntity)

    @Query("SELECT * FROM jokes ORDER BY id DESC")
    fun getAllJokes(): Flow<List<JokeEntity>>
}
