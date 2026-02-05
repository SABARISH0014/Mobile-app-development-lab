package com.example.exercise4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View Binding
        val spinnerCardType = findViewById<Spinner>(R.id.spinnerCardType)
        val spinnerState = findViewById<Spinner>(R.id.spinnerState)
        val spinnerCountry = findViewById<Spinner>(R.id.spinnerCountry)
        val etCardNumber = findViewById<EditText>(R.id.etCardNumber)
        val layoutCardNum = findViewById<TextInputLayout>(R.id.layoutCardNum)
        val tvCardTypeError = findViewById<TextView>(R.id.textView2) // Error text for spinner
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // 1. Populate Spinners
        val cardTypes = arrayOf("Card type", "Visa", "MasterCard", "Amex")
        val states = arrayOf("State", "California", "New York", "Texas")
        val countries = arrayOf("Country", "United States", "Canada", "India")

        setupSpinner(spinnerCardType, cardTypes)
        setupSpinner(spinnerState, states)
        setupSpinner(spinnerCountry, countries)

        // 2. Submit Logic
        btnSubmit.setOnClickListener {
            val cardNum = etCardNumber.text.toString().trim()
            val selectedCard = spinnerCardType.selectedItem.toString()

            var isValid = true

            // Validation for Spinner
            if (spinnerCardType.selectedItemPosition == 0) {
                tvCardTypeError.visibility = View.VISIBLE
                isValid = false
            } else {
                tvCardTypeError.visibility = View.GONE
            }

            // Validation for Card Number (16 digits)
            if (cardNum.length != 16) {
                layoutCardNum.error = "Enter a 16-digit number"
                isValid = false
            } else {
                layoutCardNum.isErrorEnabled = false // Clears error styling
            }

            if (isValid) {
                // Pass data to the next screen using Intent
                val intent = Intent(this, DisplayActivity::class.java).apply {
                    putExtra("CARD_TYPE", selectedCard)
                    putExtra("CARD_NUM", cardNum)
                }
                startActivity(intent)
            }
        }
    }

    private fun setupSpinner(spinner: Spinner, data: Array<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data)
        spinner.adapter = adapter
    }
}