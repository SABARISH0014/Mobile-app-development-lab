package com.example.exercise12

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// 1. Renamed class from CookieActivity to MainActivity
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Ensuring this points to 'activity_main.xml'
        setContentView(R.layout.activity_main)

        // Initialize views
        val imgCharacter = findViewById<ImageView>(R.id.imgCharacter)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val btnEat = findViewById<Button>(R.id.btnEat)

        // Set initial state
        tvStatus.text = "I'm so hungry"
        imgCharacter.setImageResource(R.drawable.unhappy_char)

        btnEat.setOnClickListener {
            // 1. Change image to happy character
            imgCharacter.setImageResource(R.drawable.happy_char)

            // 2. Change text to "I'm so full"
            tvStatus.text = "I'm so full"

            // Optional: Update button to show the action is complete
            btnEat.text = "Done!"
            btnEat.isEnabled = false
        }
    }
}