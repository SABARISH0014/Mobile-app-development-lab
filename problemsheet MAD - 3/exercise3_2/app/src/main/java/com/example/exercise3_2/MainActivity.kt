package com.example.exercise3_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val tvRatingStatus = findViewById<TextView>(R.id.tvRatingStatus)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSend = findViewById<Button>(R.id.btnSend)

        // 1. Handle Rating Changes
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            // Convert float rating to integer (1, 2, 3...)
            val starCount = rating.toInt()

            // Set text based on required logic
            tvRatingStatus.text = when (starCount) {
                5 -> "Awesome. I love it"
                4 -> "Good. Enjoyed it"
                3 -> "Satisfied."
                2 -> "Not good. Need improvement"
                1 -> "Disappointed. Very poor"
                else -> ""
            }
        }

        // 2. Handle Button Click
        btnSend.setOnClickListener {
            val message = etMessage.text.toString().trim()
            val rating = ratingBar.rating

            // Validation: Message is required
            if (message.isEmpty()) {
                etMessage.error = "Message is required"
                return@setOnClickListener
            }

            // Navigate to Next Screen
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("USER_MESSAGE", message)
            intent.putExtra("USER_RATING", rating)
            startActivity(intent)
        }
    }
}