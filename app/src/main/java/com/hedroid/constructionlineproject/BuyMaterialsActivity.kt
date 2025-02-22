package com.hedroid.constructionlineproject

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hedroid.constructionlineproject.Adapter.MaterialsAdapter
import com.hedroid.constructionlineproject.DataHelper.CartDatabaseHelper
import com.hedroid.constructionlineproject.Model.MaterialItem
import com.hedroid.constructionlineproject.databinding.ActivityBuyMaterialsBinding


class BuyMaterialsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyMaterialsBinding
    private lateinit var cartDatabaseHelper : CartDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyMaterialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartDatabaseHelper = CartDatabaseHelper(this)

        val materials = listOf(
            MaterialItem("Cement", "Rs. 100 per bag", R.drawable.cement),
            MaterialItem("Bricks", "Rs. 100 per brick", R.drawable.bricks),
            MaterialItem("Steel Rods", "Rs. 100 per kg", R.drawable.steel),
            MaterialItem("Sand", "Rs. 100 per ton", R.drawable.sand)
        )

        val adapter = MaterialsAdapter(materials) { material ->
            cartDatabaseHelper.addItem(material.name)
            Toast.makeText(this, "${material.name} added to cart!", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewMaterials.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMaterials.adapter = adapter
    }
}


