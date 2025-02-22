package com.hedroid.constructionlineproject.Model

data class SellFlatItem(
    val id: Int? = null,
    val name: String,
    val address: String,
    val price: Int,
    val imageRes: Int // Store drawable resource ID
)
