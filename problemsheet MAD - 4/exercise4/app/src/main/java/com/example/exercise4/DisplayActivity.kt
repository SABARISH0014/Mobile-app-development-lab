package com.example.exercise4

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Root Layout
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(android.graphics.Color.parseColor("#F5F5F5"))
        }

        // Receipt Card
        val card = MaterialCardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(50, 50, 50, 50) }
            radius = 24f
            cardElevation = 12f
        }

        // Data Display
        val tv = TextView(this).apply {
            val type = intent.getStringExtra("CARD_TYPE") ?: "N/A"
            val num = intent.getStringExtra("CARD_NUM") ?: "0000"

            // Masking the card for better design: XXXX XXXX XXXX 1234
            val maskedNum = "**** **** **** ${num.takeLast(4)}"

            text = "Payment Success!\n\nMethod: $type\nCard: $maskedNum"

            // FIXED: Using TypedValue to set SP correctly
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            setPadding(60, 80, 60, 80)
            gravity = Gravity.CENTER
            setTextColor(android.graphics.Color.DKGRAY)
        }

        card.addView(tv)
        root.addView(card)
        setContentView(root)
    }
}