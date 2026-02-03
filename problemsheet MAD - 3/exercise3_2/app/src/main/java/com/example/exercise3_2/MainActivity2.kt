package com.example.exercise3_2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // 1. Get the data from the Intent
        val message = intent.getStringExtra("USER_MESSAGE")

        // (Optional) If you also passed the rating:
        // val rating = intent.getFloatExtra("USER_RATING", 0f)

        // 2. Find the TextView
        val tvDisplay = findViewById<TextView>(R.id.tvDisplayMessage)

        // 3. Set the text
        tvDisplay.text = message
    }
}