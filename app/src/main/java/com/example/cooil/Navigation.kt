package com.example.cooil

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cooil.ui.screens.Screen1
import com.example.cooil.ui.screens.Screen2

@Composable
fun Navigation(navController: NavHostController, mascotas: MutableList<Mascota>) {
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") { Screen1(navController, mascotas) }
        composable("screen2") { Screen2(navController, mascotas) }
    }
}
