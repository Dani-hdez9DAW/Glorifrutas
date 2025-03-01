package com.example.glorifrutas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.glorifrutas.R
import com.example.glorifrutas.viewmodel.FrutasViewModel

@Composable
fun InfoFrutaScreen(
    navController: NavHostController,
    frutaId: Int,
    viewModel: FrutasViewModel = viewModel()
) {
    val frutas by viewModel.frutas.collectAsState()
    val fruta = frutas.find { it.id == frutaId }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.orange_200))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Información de la Fruta",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 30.sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    ) { innerPadding ->
        fruta?.let { fruta ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(fruta.colorResId))
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Nº " + String.format("%02d", fruta.id),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = fruta.colorResId)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Image(
                                painter = painterResource(id = fruta.imagenResId),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .background(Color.White)
                                    .padding(top = 2.dp) // Adjust the top padding to move the image up
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = fruta.nombre, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Divider(thickness = 1.dp, color = Color.Black)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Descripción", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = fruta.descripcionLarga)
                            Spacer(modifier = Modifier.height(6.dp))
                            Divider(thickness = 1.dp, color = Color.Black)
                            Text(text = "Características", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = fruta.datoCurioso)
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Cosecha principal: ", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                Text(text = fruta.lugarCosecha)
                            }

                            Text(text = "Valoración", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                repeat(5) { index ->
                                    StarIcon(
                                        filled = index < fruta.valoracion,
                                        fruitId = fruta.id,
                                        modifier = Modifier.padding(1.dp) // Less separation
                                    )
                                }
                            }
                            Divider(thickness = 1.dp, color = Color.Black)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.purple_700),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Atrás",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun StarIcon(filled: Boolean, fruitId: Int, modifier: Modifier = Modifier) {
    val color = if (fruitId == 2) Color(R.color.purple_200) else Color.Yellow
    Canvas(modifier = modifier.size(48.dp)) { // Increase the size of the star
        val starPath = androidx.compose.ui.graphics.Path().apply {
            moveTo(24f, 0f)
            lineTo(30f, 15f)
            lineTo(48f, 18f)
            lineTo(36f, 30f)
            lineTo(39f, 48f)
            lineTo(24f, 39f)
            lineTo(9f, 48f)
            lineTo(12f, 30f)
            lineTo(0f, 18f)
            lineTo(18f, 15f)
            close()
        }
        drawPath(
            path = starPath,
            color = if (filled) color else Color.Transparent,
            style = androidx.compose.ui.graphics.drawscope.Fill
        )
        drawPath(
            path = starPath,
            color = Color.Black,
            style = Stroke(width = 2f) // Adjust the stroke width for aesthetics
        )
    }
}