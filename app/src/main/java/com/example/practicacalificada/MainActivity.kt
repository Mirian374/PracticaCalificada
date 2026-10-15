package com.example.practicacalificada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ControlAforoScreen()
            }
        }
    }
}

@Composable
fun ControlAforoScreen() {
    // Usamos mutableIntStateOf para optimizar enteros, tal como sugiere Android Studio
    var personasAdentro by remember { mutableIntStateOf(0) }
    var ultimoRegistrado by remember { mutableStateOf("Ninguno") }
    var nombreInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- TÍTULO Y CONTADORES ---
        Text(
            text = "Control de Aforo",
            fontSize = 28.sp,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Personas adentro: $personasAdentro",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Último registrado: $ultimoRegistrado",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // --- FORMULARIO DE REGISTRO ---
        OutlinedTextField(
            value = nombreInput,
            onValueChange = { nombreInput = it },
            label = { Text("Nombre del invitado") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (nombreInput.isNotBlank()) {
                    ultimoRegistrado = nombreInput.trim()
                    personasAdentro += 1
                    nombreInput = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Text("Registrar e Ingresar")
        }

        // --- FILA DE ACCIONES RÁPIDAS ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    if (personasAdentro > 0) {
                        personasAdentro -= 1
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.weight(1f) // Corregido de 1.f a 1f
            ) {
                Text("Salida (-1)")
            }

            Button(
                onClick = {
                    personasAdentro = 0
                    ultimoRegistrado = "Ninguno"
                    nombreInput = ""
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                ),
                modifier = Modifier.weight(1f) // Corregido de 1.f a 1f
            ) {
                Text("Reiniciar")
            }
        }
    }
}