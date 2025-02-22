package com.hedroid.constructionlineproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.hedroid.constructionlineproject.DataHelper.CartDatabaseHelper
import com.hedroid.constructionlineproject.databinding.ActivityOrderSummaryBinding

class OrderSummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderSummaryBinding
    private lateinit var cartDatabaseHelper: CartDatabaseHelper

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartDatabaseHelper = CartDatabaseHelper(this)
        val cartItems = cartDatabaseHelper.getCartItems()
        val totalPrice = cartItems.size * 100 // Assuming each item costs Rs. 100

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cartItems)
        binding.listViewOrderSummary.adapter = adapter

        binding.tvTotalPrice.text = "Total Price: Rs. $totalPrice"

        binding.btnConfirmOrder.setOnClickListener {
            cartDatabaseHelper.clearCart()
            Toast.makeText(this, "Order Confirmed! Cart is now empty.", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}
