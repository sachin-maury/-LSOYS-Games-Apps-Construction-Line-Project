package com.hedroid.constructionlineproject

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter

import androidx.appcompat.app.AppCompatActivity
import com.hedroid.constructionlineproject.DataHelper.BookingDatabaseHelper

import com.hedroid.constructionlineproject.databinding.ActivityViewBookingsBinding

class ViewBookingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBookingsBinding
    private lateinit var bookingDatabaseHelper: BookingDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBookingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookingDatabaseHelper = BookingDatabaseHelper(this)
        val bookings = bookingDatabaseHelper.getBookings()

        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, bookings)
        binding.listViewBookings.adapter = adapter
    }
}