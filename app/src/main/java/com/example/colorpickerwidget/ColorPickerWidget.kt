package com.example.colorpickerwidget

import android.content.Context
import android.graphics.Color
import android.graphics.Outline
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

// Interface for the color selection event listener
interface OnColorSelectedListener {
    fun onColorSelected(color: String)
}

class ColorPickerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val colors = listOf(
        "#7ED7C1",
        "#F0DBAF",
        "#DC8686",
        "#B06161"
    )
    // List of color view representations
    private val colorIndicators: MutableList<View> = mutableListOf()
    // Container for color views
    private val colorIndicatorContainer: LinearLayout
    // Listener for the color selection event
    private var colorSelectedListener: OnColorSelectedListener? = null

    init {
        // Initialize the container for color views
        colorIndicatorContainer = LinearLayout(context, attrs)
        // Set a foreground border for the container using a drawable from resources
        colorIndicatorContainer.foreground =
            ContextCompat.getDrawable(context, R.drawable.color_picker_widget)
        // Set horizontal orientation for the container
        colorIndicatorContainer.orientation = HORIZONTAL

        // Create color views and add them to the container
        colors.forEachIndexed { index, color ->
            val colorView = View(context)
            // Set the background color of the view
            colorView.setBackgroundColor(Color.parseColor(color))
            // Attach a listener to handle the color selection event
            colorView.setOnClickListener {
                onColorSelected(index)
            }

            // Define layout parameters for each color view, the last colors divide the free space in half
            val params = LayoutParams(
                when (index) {
                    0 -> dpToPixels(100f)
                    1 -> dpToPixels(200f)
                    else -> 0
                },
                LayoutParams.MATCH_PARENT
            )
            params.weight = if (index > 1) 1f else 0f
            params.height = dpToPixels(26f)
            colorView.layoutParams = params

            // Add the color view to lists and the container
            colorIndicators.add(colorView)
            colorIndicatorContainer.addView(colorView)

        }
        // Add the container to the main layout
        addView(colorIndicatorContainer)
        setCorners()
    }
    //because you can't just apply rounding to LinearLayout.
    private fun setCorners() {
        val mOutlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                val cornerRadius = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    8f,
                    resources.displayMetrics
                ).toInt()
                outline.setRoundRect(0, 0, view.width, view.height, cornerRadius.toFloat())
            }
        }

        colorIndicatorContainer.apply {
            outlineProvider = mOutlineProvider
            clipToOutline = true
        }
    }

    // Method to convert dp to pixels, because LayoutParams distorts the normal dp assignment.
    private fun dpToPixels(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        ).toInt()
    }

    // Method to set the color selection event listener
    fun setOnColorSelectedListener(listener: OnColorSelectedListener) {
        this.colorSelectedListener = listener
    }

    // Method to handle the color selection event
    private fun onColorSelected(index: Int) {
        colorSelectedListener?.onColorSelected(colors[index])
    }
}