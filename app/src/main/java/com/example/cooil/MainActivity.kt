package com.example.cooil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.cooil.ui.theme.CooilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CooilTheme {
                val mascotas = remember { mutableStateListOf<Mascota>() }
                val navController = rememberNavController()
                Navigation(navController, mascotas)
            }
        }
    }
}
