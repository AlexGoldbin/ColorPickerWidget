package com.example.colorpickerwidget


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val colors = listOf(
    "#7ED7C1",
    "#F0DBAF",
    "#DC8686",
    "#B06161"
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorList() {
    // State to hold the currently selected color
    var selectedColor by remember { mutableStateOf<String>("n/a") }

    // Outer Column to contain the entire UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
            .padding(16.dp)
    ) {
        Text(
            text = "Color Picker",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .weight(1f)
                .align(CenterHorizontally)
        )
        // FlowRow to display color boxes
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .weight(1f)
                .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .clip(shape = RoundedCornerShape(8.dp))
        ) {
            for ((index, color) in colors.withIndex()) {
                // Modifier for each box based on its index "example Color 1 width = 100 Color 2 width = 200"
                val boxModifier = when (index) {
                    0 -> Modifier
                        .width(100.dp)
                    1 -> Modifier
                        .width(200.dp)
                    else -> Modifier
                        .weight(1f)
                }
                // Box composable representing a color
                Box(
                    modifier = boxModifier
                        .height(26.dp)
                        .background(Color(android.graphics.Color.parseColor(color)))
                        .clickable { selectedColor = color }
                )
            }
        }
        // Column to display the picked color information
        Column(
            modifier = Modifier
                .weight(1f)
                .align(CenterHorizontally)
        ) {

            Text(
                text = "Picked Color",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            // Text displaying the currently selected color
            Text(
                text = selectedColor,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
        }

    }
}

// Preview function for the ColorList composable
@Preview
@Composable
fun ColorlistPrewie() = ColorList()


