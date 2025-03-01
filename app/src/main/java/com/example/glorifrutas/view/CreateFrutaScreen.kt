package com.example.glorifrutas.view

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.glorifrutas.R
import com.example.glorifrutas.model.Fruta
import com.example.glorifrutas.viewmodel.FrutasViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateFrutaScreen(navController: NavHostController, viewModel: FrutasViewModel = viewModel()) {
    var nombre by remember { mutableStateOf("") }
    var colorResId by remember { mutableStateOf(0) }
    var descripcionCorta by remember { mutableStateOf("") }
    var descripcionLarga by remember { mutableStateOf("") }
    var imagenResId by remember { mutableStateOf(0) }
    var valoracion by remember { mutableStateOf(1f) }
    var datoCurioso by remember { mutableStateOf("") }
    var lugarCosecha by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    val context = LocalContext.current

    // List of colors from colors.xml
    val colors = listOf(
        R.color.red_200,
        R.color.green_200,
        R.color.blue_200,
        R.color.purple_200,
        R.color.orange_200,
        R.color.yellow_200,
        R.color.pink_200,
        R.color.teal_200,
    )

    // Generate a random color
    LaunchedEffect(Unit) {
        colorResId = colors[Random.nextInt(colors.size)]
    }

    // Image picker launcher
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val mimeType = context.contentResolver.getType(it)
            if (mimeType == "image/png" || mimeType == "image/jpeg" || mimeType == "image/jpg") {
                // Set imagenResId to the selected image resource ID
                imagenResId = it.toString().toInt() // This is just a placeholder, you need to handle the image resource properly
                Toast.makeText(context, "Imagen seleccionada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Formato de imagen no soportado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Fruta") },
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFCC80))
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                TextField(
                    value = descripcionCorta,
                    onValueChange = { descripcionCorta = it },
                    label = { Text("Descripción Corta") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                TextField(
                    value = descripcionLarga,
                    onValueChange = { descripcionLarga = it },
                    label = { Text("Descripción Larga") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(150.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(autoCorrectEnabled = true)
                )
                TextField(
                    value = datoCurioso,
                    onValueChange = { datoCurioso = it },
                    label = { Text("Dato Curioso") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(150.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(autoCorrectEnabled = true)
                )
                TextField(
                    value = lugarCosecha,
                    onValueChange = { lugarCosecha = it },
                    label = { Text("Lugar de Cosecha") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                // Rating slider
                Text("Valoración: ${valoracion.toInt()}")
                Slider(
                    value = valoracion,
                    onValueChange = { valoracion = it },
                    valueRange = 1f..5f,
                    steps = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                // Image selection field
                Button(
                    onClick = {
                        imagePickerLauncher.launch("image/*")
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Seleccionar Imagen")
                }

                Button(
                    onClick = {
                        val frutaId = viewModel.getNextFrutaId()
                        if (imagenResId == 0) {
                            imagenResId = R.drawable.logoglorifrutas // Set default image if none selected
                        }
                        val fruta = Fruta(
                            id = frutaId,
                            nombre = nombre,
                            colorResId = colorResId,
                            descripcionCorta = descripcionCorta,
                            descripcionLarga = descripcionLarga,
                            imagenResId = imagenResId,
                            valoracion = valoracion.toInt(),
                            datoCurioso = datoCurioso,
                            lugarCosecha = lugarCosecha,
                        )
                        viewModel.agregarFruta(fruta)
                        Toast.makeText(context, "Fruta añadida", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Guardar")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Guardar")
                }
            }
        }
    )
}