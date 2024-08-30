package com.example.lab1_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1_2.databinding.ActivitySecondActivityBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivitySecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the button text passed from MainActivity and set it to the TextView
        val buttonText = intent.getStringExtra("BUTTON_TEXT")
        binding.textView.text = buttonText
    }
}
