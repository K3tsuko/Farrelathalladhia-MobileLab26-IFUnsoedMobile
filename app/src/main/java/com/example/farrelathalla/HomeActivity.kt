package com.example.farrelathalla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.farrelathalla.ui.screen.HomeScreen
import com.example.farrelathalla.ui.theme.JualanTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JualanTheme {
                HomeScreen() // Memanggil halaman daftar produk yang baru saja dibuat
            }
        }
    }
}