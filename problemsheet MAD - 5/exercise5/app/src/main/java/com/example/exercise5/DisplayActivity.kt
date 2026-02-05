package com.example.exercise5

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        // Initialize the view
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Retrieve data from Intent
        val name = intent.getStringExtra("NAME")
        val roll = intent.getStringExtra("ROLL")
        val gender = intent.getStringExtra("GENDER")
        val prog = intent.getStringExtra("PROG")
        val hobbies = intent.getStringExtra("HOBBIES")
        val dob = intent.getStringExtra("DOB")

        // Format and display
        val result = """
            ✨ Registration Details ✨
            ---------------------------
            Name: $name
            Roll No: $roll
            Gender: $gender
            Programme: $prog
            Hobbies: $hobbies
            Date of Birth: $dob
        """.trimIndent()

        tvResult.text = result
    }
}