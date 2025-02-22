package com.hedroid.constructionlineproject

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.hedroid.constructionlineproject.DataHelper.BookingDatabaseHelper
import com.hedroid.constructionlineproject.databinding.ActivityBookDesignerBinding
import java.util.Calendar

class BookDesignerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDesignerBinding
    private lateinit var bookingDatabaseHelper: BookingDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDesignerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookingDatabaseHelper = BookingDatabaseHelper(this)

        binding.btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.etSelectedDate.setText(selectedDate)
            }, year, month, day)
            datePicker.show()
        }

        binding.btnBookMeeting.setOnClickListener {
            val name = binding.etDesignerName.text.toString()
            val date = binding.etSelectedDate.text.toString()

            if (name.isNotEmpty() && date.isNotEmpty()) {
                bookingDatabaseHelper.addBooking(name, date)
                Toast.makeText(this, "Booking confirmed!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
            }
            binding.viewMeeting.setOnClickListener{
                startActivity(Intent(this,ViewBookingsActivity::class.java))
                finish()
            }
        }

    }
}