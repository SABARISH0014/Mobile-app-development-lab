package com.example.exercise3_1

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Setup Toolbar
        val toolbar = findViewById<Toolbar>(R.id.topToolbar)

        // This connects the menu (search icon + 3 dots)
        toolbar.inflateMenu(R.menu.top_menu)

        // 2. Setup Search Interaction
        val searchItem = toolbar.menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as? SearchView
        searchView?.queryHint = "Search..."

        // 3. Setup Button
        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            Toast.makeText(this, "Opening Registration Form", Toast.LENGTH_SHORT).show()
        }
    }
}