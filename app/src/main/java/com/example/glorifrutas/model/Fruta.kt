package com.example.glorifrutas.model
import androidx.annotation.ColorRes

data class Fruta(
    val id: Int = 0,
    val nombre: String = "",
    val colorResId: Int = 0,
    val descripcionCorta: String = "",
    val descripcionLarga: String = "",
    val imagenResId: Int = 0,
    val valoracion: Int = 0,
    val datoCurioso: String = "",
    val lugarCosecha: String = ""
)