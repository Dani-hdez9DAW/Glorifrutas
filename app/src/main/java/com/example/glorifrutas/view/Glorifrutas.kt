package com.example.glorifrutas.view

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.glorifrutas.R
import com.example.glorifrutas.repository.FrutasRepository
import com.example.glorifrutas.ui.theme.GlorifrutasTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var firebaseRef: DatabaseReference
    private val frutasRepository = FrutasRepository()
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializa Firebase
        Firebase.database.setPersistenceEnabled(true)
        firebaseRef = Firebase.database.reference

        // Configura Firebase Database
        val database = Firebase.database
        val myRef = database.getReference("message")
        frutasRepository.guardarFrutasEnFirestore()
        myRef.setValue("Base de datos conectada")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(String::class.java)
                Log.d("MainActivity", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainActivity", "Failed to read value.", error.toException())
            }
        })

        setContent {
            GlorifrutasTheme {
                mediaPlayer = MediaPlayer.create(this, R.raw.mainscreen)
                mediaPlayer?.isLooping = true
                mediaPlayer?.start()
                val navController = rememberNavController()
                NavHost(navController, startDestination = "main") {
                    composable("main") { MainScreen(navController) }
                    composable("menuFrutas") { MenuFrutasScreen(navController) }
                    composable("frutasDetail/{frutaId}") { backStackEntry ->
                        val frutaId = backStackEntry.arguments?.getString("frutaId")?.toInt() ?: 0
                        InfoFrutaScreen(navController, frutaId)
                    }
                    composable("CreateFrutaScreen") { CreateFrutaScreen(navController) }
                    composable("frutasUpdate/{frutaId}") { backStackEntry ->
                        val frutaId = backStackEntry.arguments?.getString("frutaId")?.toInt() ?: 0
                        UpdateFrutaScreen(navController, frutaId)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val backgroundColor = Color(0xFFF5E1C3)
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoglorifrutas),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.orange_200),
                        modifier = Modifier.padding(bottom = 32.dp)
                    )
                    LaunchedEffect(Unit) {
                        val delayTime = Random.nextLong(2000, 5000)
                        delay(delayTime)
                        navController.navigate("menuFrutas")
                    }
                } else {
                    Button(
                        onClick = {
                            isLoading = true
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange_200)),
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .size(width = 200.dp, height = 60.dp)
                    ) {
                        Text(
                            text = "Comenzar",
                            color = colorResource(id = R.color.gray_700),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GlorifrutasTheme {
        val navController = rememberNavController()
        Scaffold {
            MainScreen(navController)
        }
    }
}