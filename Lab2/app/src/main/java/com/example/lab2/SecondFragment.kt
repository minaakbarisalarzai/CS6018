// Package declaration: Defines the package in which this class resides.
package com.example.lab2

// Import statements: These bring in necessary classes from Android libraries.
// `Bitmap` represents a bitmap image.
// `Bundle` is used to pass data between fragments and activities.
// `LayoutInflater` is used to convert XML layout files into View objects.
// `MotionEvent` represents a touch event on the screen.
// `View` and `ViewGroup` represent the basic building blocks of the user interface.
// `Fragment` is the base class for creating fragments in Android.

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// SecondFragment class declaration: This class represents a fragment in the app.
// It extends `Fragment`, meaning it inherits all functionalities of a fragment.
class SecondFragment : Fragment() {

    // Declare a variable for the custom drawing view.
    // `lateinit var` means the variable will be initialized later before it's used.
    // `customDrawingView` will hold a reference to the CustomDrawingView.
    private lateinit var customDrawingView: CustomDrawingView

    // Companion object: A singleton object that belongs to the class rather than instances of the class.
    // This object will hold static variables or methods that can be accessed without creating an instance of the class.
    companion object {
        // Static variable to hold the saved bitmap.
        // This variable will persist across different instances of SecondFragment.
        var savedBitmap: Bitmap? = null
    }

    // onCreateView method: Called to create the view hierarchy associated with the fragment.
    // It inflates the fragment's layout and initializes UI components.
    override fun onCreateView(
        inflater: LayoutInflater,       // The inflater used to inflate the fragment's layout.
        container: ViewGroup?,          // The parent view that the fragment's UI should be attached to.
        savedInstanceState: Bundle?     // The previous state of the fragment, if available.
    ): View? {
        // Inflate the layout for this fragment.
        // `R.layout.fragment_second` refers to the XML layout file for this fragment.
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        // Find the custom drawing view in the inflated layout.
        // `view.findViewById` looks up the view by its ID.
        // `R.id.custom_drawing_view` refers to the ID defined in the fragment's XML layout.
        customDrawingView = view.findViewById(R.id.custom_drawing_view)

        // If a bitmap was saved previously, set it to the custom drawing view.
        // `savedBitmap?.let` is a safe call that only executes the block if `savedBitmap` is not null.
        savedBitmap?.let {
            customDrawingView.setBitmap(it)  // Restores the saved bitmap to the drawing view.
        }

        // Set an OnTouchListener on the custom drawing view.
        // The listener handles touch events, such as drawing on the view.
        customDrawingView.setOnTouchListener { _, event ->
            // Check the type of touch event.
            // If the event is a touch down or move action, it adds a touch point to the drawing.
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                customDrawingView.addTouch(event.x, event.y)  // Draws on the view at the touch location.
                true  // Returns true to indicate the event was handled.
            } else {
                false  // Returns false to indicate the event was not handled.
            }
        }

        // Return the view to be displayed in the fragment.
        return view
    }

    // onPause method: Called when the fragment is paused.
    // Typically, this is where we should save any persistent state.
    override fun onPause() {
        super.onPause()
        // Save the current bitmap from the custom drawing view before the fragment is paused.
        // This ensures that the drawing is preserved when the user navigates away and comes back.
        savedBitmap = customDrawingView.getBitmap()
    }
}
