package com.hedroid.constructionlineproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.hedroid.constructionlineproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuyLand.setOnClickListener {
            startActivity(Intent(this, BuyLandActivity::class.java))
        }

        binding.btnBuyMaterials.setOnClickListener {
            startActivity(Intent(this, BuyMaterialsActivity::class.java))
        }

        binding.btnBookDesigner.setOnClickListener {
            startActivity(Intent(this, BookDesignerActivity::class.java))
        }

        binding.btnSellFlats.setOnClickListener {
            startActivity(Intent(this, SellFlatsActivity::class.java))
        }



        binding.btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        binding.btnCheckout.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }
    }
}