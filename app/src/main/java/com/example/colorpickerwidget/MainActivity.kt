package com.example.colorpickerwidget

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.colorpickerwidget.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnColorSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.colorPickerWidget.setOnColorSelectedListener(this)

        binding.btnAppCompose.setOnClickListener {
            val intent = Intent(it.context, ComposeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onColorSelected(color: String) {
        binding.tvColor.text = color
    }
}