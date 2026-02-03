package com.example.exercise22

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Variables to hold calculation data
    private lateinit var etInput: EditText
    private var firstNum: Double = 0.0
    private var operator: String = ""
    private var isNewOp: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etInput = findViewById(R.id.etInput)

        // Setup Number Buttons
        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        )

        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener { view ->
                onNumberClick(view)
            }
        }

        // Setup Operator Buttons
        val opButtons = listOf(R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv)
        for (id in opButtons) {
            findViewById<Button>(id).setOnClickListener { view ->
                onOperatorClick(view)
            }
        }

        // Setup Equal Button
        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            onEqualClick()
        }

        // Setup Clear Button
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            etInput.setText("")
            firstNum = 0.0
            operator = ""
            isNewOp = true
        }
    }

    // Function 1: Handle Number Clicks
    private fun onNumberClick(view: View) {
        if (isNewOp) {
            etInput.setText("")
        }
        isNewOp = false

        val button = view as Button
        var currentText = etInput.text.toString()
        val btnText = button.text.toString()

        // Prevent multiple dots
        if (btnText == "." && currentText.contains(".")) {
            return
        }

        currentText += btnText
        etInput.setText(currentText)
    }

    // Function 2: Handle Operator Clicks (+, -, *, /)
    private fun onOperatorClick(view: View) {
        val button = view as Button
        if (etInput.text.isNotEmpty()) {
            firstNum = etInput.text.toString().toDouble()
        }
        operator = button.text.toString()
        isNewOp = true
    }

    // Function 3: Calculate Result
    private fun onEqualClick() {
        if (etInput.text.isEmpty()) return

        val secondNum = etInput.text.toString().toDouble()
        var result = 0.0

        when (operator) {
            "+" -> result = firstNum + secondNum
            "-" -> result = firstNum - secondNum
            "*" -> result = firstNum * secondNum
            "/" -> result = firstNum / secondNum
        }

        // Remove decimal if it's a whole number (e.g., 5.0 -> 5)
        if (result % 1 == 0.0) {
            etInput.setText(result.toInt().toString())
        } else {
            etInput.setText(result.toString())
        }

        isNewOp = true
    }
}