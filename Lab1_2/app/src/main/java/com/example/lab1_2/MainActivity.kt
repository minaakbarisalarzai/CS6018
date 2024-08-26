package com.example.lab1_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Create buttons
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)

        // Define a lambda function - 'buttonClickListener' that takes a single parameter 'buttonText' of type String.
        // This function is used to handle click events for each button in the MainActivity.
        val buttonClickListener = { buttonText: String ->

            // Create an Intent to start the SecondActivity.
            // 'this' -  current instance of MainActivity,
            // and SecondActivity::class.java specifies the class of the activity to start.
            val intent = Intent(this, SecondActivity::class.java)

            // Put an extra value into the Intent - key-value pair,
            // where "BUTTON_TEXT" is the key and 'buttonText' is the value.
            // This will allow the SecondActivity to access the text of the button that was clicked.
            intent.putExtra("BUTTON_TEXT", buttonText)

            // Start the SecondActivity using the Intent. This will cause the app to navigate to the SecondActivity,
            // and the extra data (buttonText) will be passed along with it.
            startActivity(intent)
        }

        // Set an OnClickListener for buttons
        // When buttons are clicked, the lambda function is executed, which calls 'buttonClickListener'.
        // The texts of buttons are converted to a String using 'text.toString()' and passed as an argument to 'buttonClickListener'.
        button1.setOnClickListener { buttonClickListener(button1.text.toString()) }
        button2.setOnClickListener { buttonClickListener(button2.text.toString()) }
        button3.setOnClickListener { buttonClickListener(button3.text.toString()) }
        button4.setOnClickListener { buttonClickListener(button4.text.toString()) }
        button5.setOnClickListener { buttonClickListener(button5.text.toString()) }
    }
}
