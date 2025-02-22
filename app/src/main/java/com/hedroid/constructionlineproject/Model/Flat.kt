package com.hedroid.constructionlineproject.Model

data class Flat(
    val name: String,
    val address: String,
    val price: Int,
    val imageRes: Int // Directly use this field instead of a getter
)
