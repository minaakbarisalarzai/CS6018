package com.example.lab5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MarbleApp(viewModel: MarbleViewModel = viewModel()) {
    // Collect the sensor values
    val xOffset by viewModel.xOffset.collectAsState()
    val yOffset by viewModel.yOffset.collectAsState()

    // BoxWithConstraints allows us to access the maxWidth and maxHeight of the screen
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val maxX = constraints.maxWidth.toFloat()
        val maxY = constraints.maxHeight.toFloat()

        // Get the current screen's density
        val density = LocalDensity.current

        // Convert the marble size (50.dp) to pixels using the LocalDensity
        val marbleSizePx = with(density) { 50.dp.toPx() }

        // Calculate safe X and Y positions so the marble stays within the screen bounds
        val safeX = (maxX / 2 + xOffset * 100).coerceIn(0f, maxX - marbleSizePx)
        val safeY = (maxY / 2 + yOffset * 100).coerceIn(0f, maxY - marbleSizePx)

        // Convert the pixel values to Dp
        val offsetX: Dp = with(density) { safeX.toDp() }
        val offsetY: Dp = with(density) { safeY.toDp() }

        // Place the marble
        Box(
            modifier = Modifier
                .offset(
                    x = offsetX,
                    y = offsetY
                )
                .size(50.dp)  // Marble size
                .background(Color.Red, CircleShape)  // Red marble as a circle
        )
    }
}
