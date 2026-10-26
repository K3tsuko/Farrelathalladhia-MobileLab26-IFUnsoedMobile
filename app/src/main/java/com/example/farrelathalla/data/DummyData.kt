package com.example.farrelathalla.data

import com.example.farrelathalla.R
import com.example.farrelathalla.model.Category
import com.example.farrelathalla.model.Product

object DummyData {
    val categories = listOf(
        Category(id = 0, name = "Semua"),
        Category(id = 1, name = "Makanan"),
        Category(id = 2, name = "Minuman"),
        Category(id = 3, name = "Kerajinan")
    )

    val products = listOf(
        // Menggunakan R.drawable.iconaplikasi_playstore sebagai gambar default sementara
        Product(id = 1, name = "Kripik Singkong", price = "Rp 15.000", imageRes = R.drawable.iconaplikasi_playstore, categoryId = 1, products_count = 50),
        Product(id = 2, name = "Kopi Susu Gula Aren", price = "Rp 18.000", imageRes = R.drawable.iconaplikasi_playstore, categoryId = 2, products_count = 20),
        Product(id = 3, name = "Tas Rajut Cantik", price = "Rp 75.000", imageRes = R.drawable.iconaplikasi_playstore, categoryId = 3, products_count = null),
        Product(id = 4, name = "Mendoan Mentah", price = "Rp 10.000", imageRes = R.drawable.iconaplikasi_playstore, categoryId = 1, products_count = 100),
        Product(id = 5, name = "Jus Alpukat", price = "Rp 12.000", imageRes = R.drawable.iconaplikasi_playstore, categoryId = 2, products_count = 15)
    )
}