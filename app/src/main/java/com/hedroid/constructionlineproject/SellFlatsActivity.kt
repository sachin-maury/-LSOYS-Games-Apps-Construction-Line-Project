package com.hedroid.constructionlineproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hedroid.constructionlineproject.Adapter.SellFlatsAdapter
import com.hedroid.constructionlineproject.DataHelper.FlatDatabaseHelper

import com.hedroid.constructionlineproject.databinding.ActivitySellFlatsBinding

class SellFlatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySellFlatsBinding
    private lateinit var flatDatabaseHelper: FlatDatabaseHelper
    private lateinit var adapter: SellFlatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellFlatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        flatDatabaseHelper = FlatDatabaseHelper(this)

        // Load flats from the database
        val flats = flatDatabaseHelper.getFlats()

        adapter = SellFlatsAdapter(ArrayList(flats)) { flat ->
            flatDatabaseHelper.addFlat(flat.name, flat.address, flat.price, flat.imageRes)
            Toast.makeText(this, "${flat.name} listed for sale!", Toast.LENGTH_SHORT).show()
            refreshFlats()
        }

        binding.recyclerViewFlats.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFlats.adapter = adapter
    }

    private fun refreshFlats() {
        val updatedFlats = flatDatabaseHelper.getFlats()
        adapter.flats.clear()
        adapter.flats.addAll(updatedFlats)
        adapter.notifyDataSetChanged()
    }
}
