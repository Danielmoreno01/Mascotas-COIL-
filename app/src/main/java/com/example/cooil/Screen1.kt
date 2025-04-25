package com.example.cooil.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cooil.Mascota

@Composable
fun Screen1(navController: NavController, mascotas: MutableList<Mascota>) {
    var nombre by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var tamano by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var fotoUrl by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF0F2027), Color(0xFF2C5364))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Registro de Mascotas", style = MaterialTheme.typography.headlineMedium, color = Color.White)

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
            OutlinedTextField(value = raza, onValueChange = { raza = it }, label = { Text("Raza") })
            OutlinedTextField(value = tamano, onValueChange = { tamano = it }, label = { Text("Tamaño") })
            OutlinedTextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") })
            OutlinedTextField(value = fotoUrl, onValueChange = { fotoUrl = it }, label = { Text("Foto URL") })

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                if (nombre.isNotBlank() && raza.isNotBlank() && tamano.isNotBlank() && edad.isNotBlank() && fotoUrl.isNotBlank()) {
                    mascotas.add(
                        Mascota(
                            id = mascotas.size + 1,
                            nombre = nombre,
                            raza = raza,
                            tamano = tamano,
                            edad = edad,
                            fotoUrl = fotoUrl
                        )
                    )
                    navController.navigate("screen2")
                }
            }) {
                Text("Registrar")
            }

            Button(
                onClick = { navController.navigate("screen2") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Ver mascotas registradas")
            }
        }
    }
}
