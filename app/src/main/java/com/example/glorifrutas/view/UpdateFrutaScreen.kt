package com.example.glorifrutas.view

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.glorifrutas.R
import com.example.glorifrutas.model.Fruta
import com.example.glorifrutas.viewmodel.FrutasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateFrutaScreen(navController: NavHostController, frutaId: Int, viewModel: FrutasViewModel = viewModel()) {
    LaunchedEffect(frutaId) {
        viewModel.obtenerFrutaPorId(frutaId)
    }

    val fruta by viewModel.fruta.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var descripcionCorta by remember { mutableStateOf("") }
    var descripcionLarga by remember { mutableStateOf("") }
    val imagenUri = remember { mutableStateOf<Uri?>(null) }
    val imagenPath = remember { mutableStateOf("") }
    val valoracion = remember { mutableStateOf(1f) }
    var datoCurioso by remember { mutableStateOf("") }
    val lugarCosecha = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(fruta) {
        fruta?.let { fruta ->
            nombre = fruta.nombre
            descripcionCorta = fruta.descripcionCorta
            descripcionLarga = fruta.descripcionLarga
            imagenPath.value = fruta.imagenResId.toString()
            valoracion.value = fruta.valoracion.toFloat()
            datoCurioso = fruta.datoCurioso
            lugarCosecha.value = fruta.lugarCosecha
        }
    }

    val isFormValid = nombre.trim().isNotBlank() && descripcionCorta.trim().isNotBlank() && descripcionLarga.trim().isNotBlank() &&
            datoCurioso.trim().isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Actualizar Fruta", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    }
                },
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFB3E5FC))
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it.trim() },
                    label = { Text("Nombre", color = Color.DarkGray) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    isError = nombre.isBlank()
                )
                TextField(
                    value = descripcionCorta,
                    onValueChange = { descripcionCorta = it.trim() },
                    label = { Text("Descripción Corta", color = Color.DarkGray) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    isError = descripcionCorta.isBlank()
                )
                TextField(
                    value = descripcionLarga,
                    onValueChange = { descripcionLarga = it.trim() },
                    label = { Text("Descripción Larga", color = Color.DarkGray) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(150.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(autoCorrectEnabled = true),
                    isError = descripcionLarga.isBlank()
                )
                TextField(
                    value = datoCurioso,
                    onValueChange = { datoCurioso = it.trim() },
                    label = { Text("Dato Curioso", color = Color.DarkGray) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(150.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(autoCorrectEnabled = true),
                    isError = datoCurioso.isBlank()
                )
                TextField(
                    value = lugarCosecha.value,
                    onValueChange = {},
                    label = { Text("Lugar de Cosecha", color = Color.DarkGray) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200),
                        disabledTextColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    enabled = false
                )
                Text("Valoración: ${valoracion.value.toInt()}", color = Color.DarkGray)
                Slider(
                    value = valoracion.value,
                    onValueChange = {},
                    valueRange = 1f..5f,
                    steps = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    enabled = false
                )
                // Image preview
                if (imagenUri.value != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imagenUri.value),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = fruta?.imagenResId ?: R.drawable.logoglorifrutas),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp)
                    )
                }

                Button(
                    onClick = {
                        val updatedFruta = Fruta(
                            id = frutaId,
                            nombre = nombre.trim(),
                            colorResId = fruta?.colorResId ?: R.color.orange_200,
                            descripcionCorta = descripcionCorta.trim(),
                            descripcionLarga = descripcionLarga.trim(),
                            imagenResId = fruta?.imagenResId ?: R.drawable.logoglorifrutas,
                            valoracion = valoracion.value.toInt(),
                            datoCurioso = datoCurioso.trim(),
                            lugarCosecha = lugarCosecha.value,
                        )
                        viewModel.actualizarFruta(updatedFruta)
                        Toast.makeText(context, "Fruta actualizada", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA)), // Purple background
                    modifier = Modifier
                        .padding(16.dp)
                        .alpha(if (isFormValid) 1f else 0.5f)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Guardar", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Guardar", color = Color.White)
                }
            }
        }
    )
}