package com.example.exercise5

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Initialize all Views from activity_main.xml
        val etName = findViewById<EditText>(R.id.etName)
        val etRollNo = findViewById<EditText>(R.id.etRollNo)
        val autoCity = findViewById<AutoCompleteTextView>(R.id.autoCity)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val rbMale = findViewById<RadioButton>(R.id.rbMale)
        val spinnerProgramme = findViewById<Spinner>(R.id.spinnerProgramme)
        val cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        val cbSports = findViewById<CheckBox>(R.id.cbSports)
        val cbMusic = findViewById<CheckBox>(R.id.cbMusic)
        val btnPickDate = findViewById<Button>(R.id.btnPickDate)
        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // 2. Setup AutoComplete
        val cities = arrayOf("New York", "London", "Tokyo", "Mumbai", "Paris")
        val cityAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cities)
        autoCity.setAdapter(cityAdapter)

        // 3. Setup Spinner
        val progs = arrayOf("B.Tech", "M.Tech", "BCA", "MCA")
        val progAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, progs)
        spinnerProgramme.adapter = progAdapter

        // 4. Date Picker Dialog
        btnPickDate.setOnClickListener {
            val c = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, day ->
                selectedDate = "$day/${month + 1}/$year"
                tvSelectedDate.text = "Selected: $selectedDate"
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        // 5. Submit Button Logic
        btnSubmit.setOnClickListener {
            if (validateForm(etName, etRollNo, rgGender)) {
                val intent = Intent(this, DisplayActivity::class.java)
                intent.putExtra("NAME", etName.text.toString())
                intent.putExtra("ROLL", etRollNo.text.toString())
                intent.putExtra("GENDER", if (rbMale.isChecked) "Male" else "Female")
                intent.putExtra("PROG", spinnerProgramme.selectedItem.toString())

                val hobbies = mutableListOf<String>()
                if (cbCoding.isChecked) hobbies.add("Coding")
                if (cbSports.isChecked) hobbies.add("Sports")
                if (cbMusic.isChecked) hobbies.add("Music")
                intent.putExtra("HOBBIES", hobbies.joinToString(", "))

                intent.putExtra("DOB", selectedDate)
                startActivity(intent)
            }
        }
    }

    // Validation Logic
    private fun validateForm(etName: EditText, etRollNo: EditText, rgGender: RadioGroup): Boolean {
        if (etName.text.isEmpty()) {
            etName.error = "Name is required"
            return false
        }
        if (etRollNo.text.isEmpty()) {
            etRollNo.error = "Roll No is required"
            return false
        }
        if (rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
            return false
        }
        if (selectedDate.isEmpty()) {
            Toast.makeText(this, "Please pick a date", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}