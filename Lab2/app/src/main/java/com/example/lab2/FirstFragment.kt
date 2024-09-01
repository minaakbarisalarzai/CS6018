// Package declaration: Defines the package in which this class resides.
package com.example.lab2

// Import statements: These bring in necessary classes from the Android libraries.
// `Bundle` is used to pass data between components.
// `LayoutInflater`, `View`, and `ViewGroup` are used to manage the layout of the fragment.
// `Button` is used to handle button UI components.
// `Fragment` is the base class for creating a fragment in Android.
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

// FirstFragment class declaration: This class represents a fragment in the app.
// It extends `Fragment`, which is a reusable portion of the UI within an activity.
class FirstFragment : Fragment() {

    // onCreateView method: This method is called when the fragment should create its view hierarchy.
    // It inflates the layout for the fragment and initializes any UI components.
    override fun onCreateView(
        inflater: LayoutInflater,    // The inflater used to convert the XML layout into a View
        container: ViewGroup?,       // The parent view that the fragment's UI should be attached to
        savedInstanceState: Bundle?  // The previous state of the fragment, if any
    ): View? {
        // Inflate the layout for this fragment.
        // `inflater` inflates the XML layout file into the corresponding View objects.
        // `container` is the parent view that the fragment's UI should be attached to.
        // `R.layout.fragment_first` refers to the XML layout file for this fragment.
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        // Find the button in the inflated layout.
        // `view.findViewById<Button>(R.id.navigate_button)` looks up the button by its ID.
        // `R.id.navigate_button` refers to the ID defined in the fragment's XML layout.
        val button = view.findViewById<Button>(R.id.navigate_button)

        // Set an OnClickListener on the button.
        // The `setOnClickListener` method is used to define what happens when the button is clicked.
        button.setOnClickListener {
            // Begin a fragment transaction to replace the current fragment with SecondFragment.
            // `parentFragmentManager` manages the fragments within the parent activity.
            parentFragmentManager.beginTransaction()
                // Replace the content of the `fragment_container` with `SecondFragment`.
                // This effectively navigates the user to the second fragment.
                .replace(R.id.fragment_container, SecondFragment())
                // Add the transaction to the back stack.
                // This allows the user to navigate back to the previous fragment by pressing the back button.
                .addToBackStack(null)
                // Commit the transaction to apply the changes.
                .commit()
        }
        // Return the view for this fragment.
        // The view is now set up with the button and its click listener.
        return view
    }
}
