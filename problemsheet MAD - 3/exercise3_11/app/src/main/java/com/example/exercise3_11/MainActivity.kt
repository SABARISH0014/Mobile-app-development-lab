package com.example.exercise3_11

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Setup Toolbar
        val toolbar = findViewById<Toolbar>(R.id.topToolbar)
        toolbar.inflateMenu(R.menu.top_menu) // Ensure you have res/menu/top_menu.xml created

        // 2. Setup Search Interaction
        val searchItem = toolbar.menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as? SearchView
        searchView?.queryHint = "Search..."

        // 3. Setup Button Logic
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            Toast.makeText(this, "Register Clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}