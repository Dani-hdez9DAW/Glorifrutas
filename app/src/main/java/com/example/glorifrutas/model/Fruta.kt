package com.example.glorifrutas.model
import androidx.annotation.ColorRes

data class Fruta(
    val id: Int,
    val nombre: String,
    @ColorRes val colorResId: Int,
    val descripcionCorta: String,
    val descripcionLarga: String,
    val imagenResId: Int,
    val valoracion: Int,
    val datoCurioso:String,
    val lugarCosecha: String,
)