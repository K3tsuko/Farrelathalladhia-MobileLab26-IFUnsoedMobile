package com.example.farrelathalla.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farrelathalla.R
import com.example.farrelathalla.data.DummyData
import com.example.farrelathalla.model.Category
import com.example.farrelathalla.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    // Default aktif ke id 1 (Makanan) agar sesuai gambar
    var selectedCategoryId by remember { mutableStateOf(1) }
    val context = LocalContext.current

    val filteredProducts = DummyData.products.filter { it.categoryId == selectedCategoryId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Daftar Produk UMKM", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                },
                actions = {
                    IconButton(onClick = { /* Aksi cart kosong */ }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Keranjang"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3AA34B), // Warna hijau menyesuaikan
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Kategori Produk",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(DummyData.categories) { category ->
                    CategoryItem(
                        category = category,
                        isSelected = category.id == selectedCategoryId,
                        onClick = { selectedCategoryId = category.id }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Daftar Produk",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredProducts) { product ->
                    val categoryName = DummyData.categories.find { it.id == product.categoryId }?.name ?: ""
                    ProductItemCard(
                        product = product,
                        categoryName = categoryName,
                        onClick = {
                            Toast.makeText(context, "Clicked: ${product.name}", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color(0xFF3AA34B) else MaterialTheme.colorScheme.surfaceVariant
    val contentColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category.name, color = contentColor, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}

@Composable
fun ProductItemCard(product: Product, categoryName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }, // Menambahkan fungsi klik
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            // Box pembungkus untuk menumpuk gambar dan label/badge kategori
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(4.dp),
                    contentScale = ContentScale.Fit
                )

                // Label kategori di pojok kanan atas gambar
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(
                            Color(0xFF76BA43),
                            shape = RoundedCornerShape(bottomStart = 8.dp, topEnd = 8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = categoryName,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 1,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.price,
                color = Color(0xFF3AA34B), // Warna harga hijau
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}