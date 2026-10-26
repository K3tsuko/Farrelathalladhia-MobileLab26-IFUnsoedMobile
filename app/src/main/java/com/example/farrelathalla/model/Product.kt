package com.example.farrelathalla.model

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageRes: Int,
    val categoryId: Int,
    val products_count: Int? = null
)