package com.example.glorifrutas.model

data class User(
    private var id: Int,
    private var nombre: String
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