package com.example.glorifrutas.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glorifrutas.model.Fruta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FrutasViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _frutas = MutableStateFlow<List<Fruta>>(emptyList())
    val frutas: StateFlow<List<Fruta>> = _frutas

    init {
        obtenerFrutas()
    }

    private fun obtenerFrutas() {
        viewModelScope.launch {
            db.collection("frutas")
                .get()
                .addOnSuccessListener { result ->
                    val listaFrutas = result.mapNotNull { it.toObject(Fruta::class.java) }
                    _frutas.value = listaFrutas
                    Log.d("FrutasViewModel", "Received frutas: $listaFrutas")
                }
                .addOnFailureListener { exception ->
                    Log.w("FrutasViewModel", "Error getting documents.", exception)
                }
        }
    }
}