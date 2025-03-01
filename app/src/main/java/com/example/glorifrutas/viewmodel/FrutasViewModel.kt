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

    private val _fruta = MutableStateFlow<Fruta?>(null)
    val fruta: StateFlow<Fruta?> = _fruta

    init {
        fetchFrutas()
    }

    private fun fetchFrutas() {
        db.collection("frutas").get()
            .addOnSuccessListener { result ->
                val frutasList = result.map { document ->
                    document.toObject(Fruta::class.java)
                }
                _frutas.value = frutasList
            }
            .addOnFailureListener { exception ->
                Log.w("FrutasViewModel", "Error getting documents: ", exception)
            }
    }

    fun obtenerFrutaPorId(frutaId: Int) {
        viewModelScope.launch {
            db.collection("frutas").document(frutaId.toString())
                .get()
                .addOnSuccessListener { document ->
                    _fruta.value = document.toObject(Fruta::class.java)
                    Log.d("FrutasViewModel", "Received fruta: ${_fruta.value}")
                }
                .addOnFailureListener { exception ->
                    Log.w("FrutasViewModel", "Error getting document.", exception)
                }
        }
    }

    fun agregarFruta(fruta: Fruta) {
        viewModelScope.launch {
            db.collection("frutas")
                .document(fruta.id.toString())
                .set(fruta)
                .addOnSuccessListener {
                    Log.d("FrutasViewModel", "Fruta añadida con ID: ${fruta.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("FrutasViewModel", "Error al añadir fruta", e)
                }
        }
    }
    fun deleteFruta(frutaId: Int) {
        val db = FirebaseFirestore.getInstance()
        db.collection("frutas").document(frutaId.toString())
            .delete()
            .addOnSuccessListener { Log.d("FrutaCard", "Fruta eliminada con éxito") }
            .addOnFailureListener { e -> Log.w("FrutaCard", "Error al eliminar la fruta", e) }
    }


    fun getNextFrutaId(): Int {
        return _frutas.value.size + 1
    }
}