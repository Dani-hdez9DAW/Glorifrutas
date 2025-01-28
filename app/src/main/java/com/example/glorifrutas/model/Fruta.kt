package com.example.glorifrutas.model

data class Fruta(
    private var id: Int,
    private var nombre: String,
    private var color: String,
    private var descripcionCorta: String,
    private var descripcionLarga: String,
    private var imagenResId: Int,
    private var valoracion: Int,
) {
    // Getters
    fun getId(): Int = id
    fun getNombre(): String = nombre
    fun getColor(): String = color
    fun getDescripcionCorta(): String = descripcionCorta
    fun getDescripcionLarga(): String = descripcionLarga
    fun getImagenResId(): Int = imagenResId
    fun getValoracion(): Int = valoracion

    // Setters
    fun setId(value: Int) {
        id = value
    }

    fun setNombre(value: String) {
        nombre = value
    }

    fun setColor(value: String) {
        color = value
    }

    fun setDescripcionCorta(value: String) {
        descripcionCorta = value
    }

    fun setDescripcionLarga(value: String) {
        descripcionLarga = value
    }

    fun setImagenResId(value: Int) {
        imagenResId = value
    }

    fun setValoracion(value: Int) {
        valoracion = value
    }
}