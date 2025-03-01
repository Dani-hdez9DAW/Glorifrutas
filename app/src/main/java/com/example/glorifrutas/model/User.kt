
package com.example.glorifrutas.model

import androidx.annotation.ColorRes

data class User(
    private var id: Int,
    private var nombre: String,
    val frutaFavorita: Fruta,
    @ColorRes val colorResId: Int
) {
    // Getters
    fun getId(): Int = id
    fun getNombre(): String = nombre

    // Setters
    fun setId(value: Int) {
        id = value
    }

    fun setNombre(value: String) {
        nombre = value
    }
}
