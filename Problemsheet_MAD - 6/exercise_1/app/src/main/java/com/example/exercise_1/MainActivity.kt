package com.example.exercise_1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: DoctorAdapter
    private var pendingPhone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sample Data
        val doctorList = listOf(
            Doctor("Dr. Arul Prasad", "Cardiology", "9876543210"),
            Doctor("Dr. Meera Krishnan", "Neurology", "8765432109"),
            Doctor("Dr. Rajesh Kumar", "Emergency", "7654321098"),
            Doctor("Dr. S. Kavitha", "Pediatrics", "6543210987"),
            Doctor("Dr. John Wilson", "Orthopedics", "5432109876")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = findViewById<SearchView>(R.id.searchView)

        adapter = DoctorAdapter(doctorList) { phone ->
            initiateCall(phone)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Search Logic [cite: 14]
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })
    }

    private fun initiateCall(phoneNumber: String) {
        pendingPhone = phoneNumber
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 101)
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pendingPhone?.let { initiateCall(it) }
        } else {
            Toast.makeText(this, "Permission denied to make calls", Toast.LENGTH_SHORT).show()
        }
    }
}