package com.example.lab4

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "joke_table")
data class JokeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val joke: String
)

