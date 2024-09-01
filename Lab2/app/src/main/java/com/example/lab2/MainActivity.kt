// Package declaration: This defines the package in which this class resides.
// It helps in organizing code and avoiding name conflicts.
package com.example.lab2

// Import statements: These bring in necessary classes from Android libraries
// `Bundle` is used to pass data between activities and fragments
// `AppCompatActivity` is a base class for activities that use modern Android features while maintaining backward compatibility.
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// MainActivity class declaration: This is the main entry point for the app.
// The class extends `AppCompatActivity`, which provides compatibility support for older Android versions.
class MainActivity : AppCompatActivity() {

    // Override the onCreate method: This method is called when the activity is first created.
    // `onCreate` is part of the activity lifecycle and is where initialization code is placed.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Call the superclass's onCreate method to perform any setup that is required by the base class.
        super.onCreate(savedInstanceState)

        // Set the content view for this activity.
        // `setContentView` inflates the layout specified by `R.layout.activity_main` and places it on the screen.
        setContentView(R.layout.activity_main)

        // Check if the activity is being created for the first time.
        // `savedInstanceState` is null if the activity is starting fresh (not being restored from a previous state).
        if (savedInstanceState == null) {
            // Begin a fragment transaction to add the FirstFragment to the activity.
            // `supportFragmentManager` manages the fragments in this activity.
            supportFragmentManager.beginTransaction()
                // Replace the content of the `fragment_container` (defined in the layout) with `FirstFragment`.
                // This allows `FirstFragment` to be displayed when the activity starts.
                .replace(R.id.fragment_container, FirstFragment())
                // Commit the transaction to apply the changes.
                // Committing finalizes the transaction and displays the fragment.
                .commit()
        }
    }
}
