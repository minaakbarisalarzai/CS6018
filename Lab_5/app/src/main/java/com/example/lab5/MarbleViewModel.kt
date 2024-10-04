package com.example.lab5

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MarbleViewModel : ViewModel() {
    // StateFlows to hold the sensor values
    private val _xOffset = MutableStateFlow(0f)
    val xOffset = _xOffset.asStateFlow()

    private val _yOffset = MutableStateFlow(0f)
    val yOffset = _yOffset.asStateFlow()

    // Method to start listening to the sensor data
    fun startListening(sensorManager: SensorManager) {
        val gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

        val sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

            // val GRAVITY_EARTH = 9.80665f  // Approximate value of gravity on Earth

            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]  // X-axis sensor value
                val y = event.values[1]  // Y-axis sensor value
                val z = event.values[2]  // Z-axis sensor value

                // Update offsets, reverse Y-axis for proper movement on screen
                _xOffset.value = x
                // Reverse Y-axis to match screen's coordinate system
                _yOffset.value = -y

                println("Sensor values - X: $x, Y: $y, Z: $z")
            }
        }

        sensorManager.registerListener(sensorEventListener, gravitySensor, SensorManager.SENSOR_DELAY_GAME)
    }
}
