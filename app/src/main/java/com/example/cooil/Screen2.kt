package com.example.cooil.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cooil.Mascota

@Composable
fun Screen2(navController: NavController, mascotas: MutableList<Mascota>) {
    var mascotaSeleccionada by remember { mutableStateOf<Mascota?>(null) }
    var mascotaAEliminar by remember { mutableStateOf<Mascota?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(listOf(Color(0xFF1C1C1C), Color(0xFF434343)))
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Lista de Mascotas", style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(mascotas) { mascota ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C3E50))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(mascota.fotoUrl),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(64.dp).padding(end = 16.dp)
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Nombre: ${mascota.nombre}", color = Color.White)
                                Text("Raza: ${mascota.raza}", color = Color.White)
                                Text("Tamaño: ${mascota.tamano}", color = Color.White)
                                Text("Edad: ${mascota.edad}", color = Color.White)
                            }
                            Column {
                                Button(onClick = { mascotaSeleccionada = mascota }) {
                                    Text("Editar")
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Button(
                                    onClick = { mascotaAEliminar = mascota },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("screen1") }) {
                Text("Volver al registro")
            }

            mascotaSeleccionada?.let { mascota ->
                EditarMascotaDialog(mascota = mascota, onDismiss = { mascotaSeleccionada = null })
            }

            mascotaAEliminar?.let { mascota ->
                ConfirmarEliminacionDialog(
                    nombre = mascota.nombre,
                    onConfirm = {
                        mascotas.remove(mascota)
                        mascotaAEliminar = null
                    },
                    onDismiss = { mascotaAEliminar = null }
                )
            }
        }
    }
}

@Composable
fun ConfirmarEliminacionDialog(nombre: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirmar eliminación") },
        text = { Text("¿Estás seguro de eliminar a $nombre? Esta acción no se puede deshacer.") },
        confirmButton = {
            Button(onClick = onConfirm, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun EditarMascotaDialog(mascota: Mascota, onDismiss: () -> Unit) {
    var nombre by remember { mutableStateOf(mascota.nombre) }
    var raza by remember { mutableStateOf(mascota.raza) }
    var tamano by remember { mutableStateOf(mascota.tamano) }
    var edad by remember { mutableStateOf(mascota.edad) }
    var fotoUrl by remember { mutableStateOf(mascota.fotoUrl) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                mascota.nombre = nombre
                mascota.raza = raza
                mascota.tamano = tamano
                mascota.edad = edad
                mascota.fotoUrl = fotoUrl
                onDismiss()
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text("Editar Mascota") },
        text = {
            Column {
                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                OutlinedTextField(value = raza, onValueChange = { raza = it }, label = { Text("Raza") })
                OutlinedTextField(value = tamano, onValueChange = { tamano = it }, label = { Text("Tamaño") })
                OutlinedTextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") })
                OutlinedTextField(value = fotoUrl, onValueChange = { fotoUrl = it }, label = { Text("Foto URL") })
            }
        }
    )
}
