package com.example.lab1_2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_activity)

        val buttonText = intent.getStringExtra("BUTTON_TEXT")
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = buttonText
    }
}
