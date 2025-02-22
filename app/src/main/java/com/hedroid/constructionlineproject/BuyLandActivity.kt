package com.hedroid.constructionlineproject

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hedroid.constructionlineproject.Adapter.MaterialsAdapter
import com.hedroid.constructionlineproject.DataHelper.CartDatabaseHelper
import com.hedroid.constructionlineproject.Model.MaterialItem
import com.hedroid.constructionlineproject.databinding.ActivityBuyLandBinding


class BuyLandActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyLandBinding
    private lateinit var cartDatabaseHelper: CartDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyLandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartDatabaseHelper = CartDatabaseHelper(this)


        val materials = listOf(
            MaterialItem("Buxar City", "Rs. 100 per feet", R.drawable.land_buy),
            MaterialItem("Buxar Mall", "Rs. 100 per feet", R.drawable.buxar_mall),
            MaterialItem("Delhi Noida", "Rs. 100 per feet", R.drawable.delhi_noida),
            MaterialItem("Chenni", "Rs. 100 per feet", R.drawable.chennai)
        )

        val adapter = MaterialsAdapter(materials) { material ->
            cartDatabaseHelper.addItem(material.name)
            Toast.makeText(this, "${material.name} added to cart!", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.btnBuyLand.setOnClickListener {
            cartDatabaseHelper.addItem("Land Purchase")
            Toast.makeText(this, "Land added to cart!", Toast.LENGTH_SHORT).show()
        }

    }
}