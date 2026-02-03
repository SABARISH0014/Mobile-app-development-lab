package com.example.exercise11

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputEditText = findViewById<EditText>(R.id.editTextText2)
        val resultTextView = findViewById<TextView>(R.id.textView8)
        val convertButton = findViewById<Button>(R.id.button3)

        convertButton.setOnClickListener {
            val inputString = inputEditText.text.toString()

            if (inputString.isNotEmpty()) {
                val fahrenheit = inputString.toDoubleOrNull()

                if (fahrenheit != null) {
                    val celsius = (fahrenheit - 32) * 5.0 / 9.0
                    val df = DecimalFormat("#.0")
                    val formattedResult = df.format(celsius)

                    val toastMessage = "Result: $formattedResult °C"
                    Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()

                    resultTextView.text = "$formattedResult °C"

                } else {
                    Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a temperature", Toast.LENGTH_SHORT).show()
            }
        }
    }
}