package com.example.exercise21

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This line loads the design we created in activity_main.xml
        setContentView(R.layout.activity_main)
    }
}