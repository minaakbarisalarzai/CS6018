package com.example.lab1_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listeners for each button using view binding
        binding.button1.setOnClickListener { buttonClickListener(binding.button1.text.toString()) }
        binding.button2.setOnClickListener { buttonClickListener(binding.button2.text.toString()) }
        binding.button3.setOnClickListener { buttonClickListener(binding.button3.text.toString()) }
        binding.button4.setOnClickListener { buttonClickListener(binding.button4.text.toString()) }
        binding.button5.setOnClickListener { buttonClickListener(binding.button5.text.toString()) }
    }

    private fun buttonClickListener(buttonText: String) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("BUTTON_TEXT", buttonText)
        startActivity(intent)
    }
}
