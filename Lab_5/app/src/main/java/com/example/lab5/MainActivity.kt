package com.example.lab5

import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve SensorManager to manage sensors
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Set the content using Jetpack Compose
        setContent {
            val marbleViewModel: MarbleViewModel = viewModel()

            // Start listening to sensor data
            marbleViewModel.startListening(sensorManager)

            // Render the UI with MarbleApp
            MarbleApp(viewModel = marbleViewModel)
        }
    }
}
