package com.example.glorifrutas.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.glorifrutas.R
import com.example.glorifrutas.ui.theme.GlorifrutasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlorifrutasTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "main") {
                    composable("main") { MainScreen(navController) }
                    composable("menuFrutas") { MenuFrutasScreen() }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoglorifrutas),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate("menuFrutas") },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange_200)),
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .size(width = 200.dp, height = 60.dp)
                ) {
                    Text(
                        text = "Comenzar",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun MenuFrutasScreen() {
    // Content for MenuFrutas screen
    Text(text = "Menu Frutas Screen")
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GlorifrutasTheme {
        val navController = rememberNavController()
        MainScreen(navController)
    }
}