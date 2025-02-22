package com.hedroid.constructionlineproject

//noinspection SuspiciousImport
import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hedroid.constructionlineproject.DataHelper.CartDatabaseHelper
import com.hedroid.constructionlineproject.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartDatabaseHelper: CartDatabaseHelper
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartDatabaseHelper = CartDatabaseHelper(this)
        loadCartItems()

        binding.btnProceedToCheckout.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }
    }

    private fun loadCartItems() {
        val cartItems = cartDatabaseHelper.getCartItems()
        adapter = ArrayAdapter(this, R.layout.simple_list_item_1, cartItems)
        binding.cartListView.adapter = adapter
    }
}