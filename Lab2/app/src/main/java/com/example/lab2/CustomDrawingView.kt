// Package declaration: Defines the package in which this class resides.
package com.example.lab2

// Import statements: These bring in necessary classes from Android libraries.
// `Context` provides access to application-specific resources and classes.
// `Bitmap` represents a bitmap image.
// `Canvas` is used to draw on a bitmap.
// `Color` is used to define colors.
// `Paint` holds the style and color information about how to draw geometries, text, and bitmaps.
// `AttributeSet` is a collection of attributes, as found associated with a tag in an XML file.
// `View` is the base class for UI components in Android.
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

// CustomDrawingView class declaration: This class represents a custom view that allows drawing on it.
// It extends `View`, which is the base class for all UI components.
class CustomDrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // Declare variables to hold the bitmap and canvas.
    // `bitmap` stores the drawing as a bitmap image.
    // `canvas` is used to draw on the bitmap.
    private var bitmap: Bitmap? = null
    private var canvas: Canvas? = null

    // Initialize a `Paint` object with specific properties.
    // `apply` is a Kotlin scope function that allows you to configure the object within its block.
    private val paint = Paint().apply {
        // Set the initial color to red.
        color = Color.RED
        // Set the stroke width for drawing.
        strokeWidth = 10f
    }

    // init block: This block is executed when the class is initialized.
    init {
        // `post` schedules the following block to be executed after the view is attached to the window.
        // This ensures that the view's width and height are available.
        post {
            // If the bitmap is not yet created, create it with the view's width and height.
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            }
            // Create a canvas that draws onto the bitmap.
            canvas = Canvas(bitmap!!)
        }
    }

    // onDraw method: Called when the view should render its content.
    // `canvas` is the canvas provided by the system where the view should draw its content.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // If the bitmap is not null, draw it onto the canvas.
        bitmap?.let {
            canvas.drawBitmap(it, 0f, 0f, null)
        }
    }

    // addTouch method: Adds a touch point to the drawing.
    // `x` and `y` are the coordinates where the user touched the screen.
    fun addTouch(x: Float, y: Float) {
        // Draw a circle at the touch point on the bitmap's canvas using the current paint.
        canvas?.drawCircle(x, y, 20f, paint)
        // Invalidate the view, causing it to be redrawn with the new content.
        invalidate()
    }

    // getBitmap method: Returns the current bitmap representing the drawing.
    fun getBitmap(): Bitmap? {
        return bitmap
    }

    // setBitmap method: Sets a new bitmap and updates the canvas to draw onto this bitmap.
    // `newBitmap` is the bitmap to set as the current drawing.
    fun setBitmap(newBitmap: Bitmap) {
        bitmap = newBitmap
        canvas = Canvas(bitmap!!)
        // Invalidate the view to redraw with the new bitmap.
        invalidate()
    }
}
