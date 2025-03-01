package com.example.glorifrutas.view

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateFrutaScreen(navController: NavHostController, viewModel: FrutasViewModel = viewModel()) {
    var nombre by remember { mutableStateOf("") }
    var colorResId by remember { mutableStateOf(0) }
    var descripcionCorta by remember { mutableStateOf("") }
    var descripcionLarga by remember { mutableStateOf("") }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }
    var imagenPath by remember { mutableStateOf<String?>(null) }
    var valoracion by remember { mutableStateOf(1f) }
    var datoCurioso by remember { mutableStateOf("") }
    var lugarCosecha by remember { mutableStateOf("") }
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
                imagenUri = it
                imagenPath = saveImageToInternalStorage(context, it)
                Toast.makeText(context, "Imagen seleccionada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Formato de imagen no soportado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val isFormValid = nombre.trim().isNotBlank() && descripcionCorta.trim().isNotBlank() && descripcionLarga.trim().isNotBlank() &&
            datoCurioso.trim().isNotBlank() && lugarCosecha.trim().isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Crear Fruta", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    }
                },
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFB3E5FC)) // Light blue background
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
                    value = lugarCosecha,
                    onValueChange = { lugarCosecha = it.trim() },
                    label = { Text("Lugar de Cosecha", color = Color.DarkGray) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = colorResource(id = R.color.purple_200),
                        unfocusedIndicatorColor = colorResource(id = R.color.purple_200)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    isError = lugarCosecha.isBlank()
                )
                // Rating slider
                Text("Valoración: ${valoracion.toInt()}", color = Color.DarkGray)
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA)), // Purple background
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Seleccionar Imagen", color = Color.White)
                }

                // Image preview
                if (imagenUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imagenUri),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.logoglorifrutas), // Replace with your default image resource
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp)
                    )
                }

                Button(
                    onClick = {
                        val frutaId = viewModel.getNextFrutaId()
                        val fruta = Fruta(
                            id = frutaId,
                            nombre = nombre.trim(),
                            colorResId = colorResId,
                            descripcionCorta = descripcionCorta.trim(),
                            descripcionLarga = descripcionLarga.trim(),
                            imagenResId = imagenPath?.hashCode() ?: R.drawable.logoglorifrutas, // Use default image if no image selected
                            valoracion = valoracion.toInt(),
                            datoCurioso = datoCurioso.trim(),
                            lugarCosecha = lugarCosecha.trim(),
                        )
                        viewModel.agregarFruta(fruta)
                        Toast.makeText(context, "Fruta añadida", Toast.LENGTH_SHORT).show()
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

fun saveImageToInternalStorage(context: Context, uri: Uri): String? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val file = File(context.filesDir, "${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}