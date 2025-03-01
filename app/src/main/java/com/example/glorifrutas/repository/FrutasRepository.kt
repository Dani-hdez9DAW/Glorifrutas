package com.example.glorifrutas.repository

import com.example.glorifrutas.R
import com.example.glorifrutas.model.Fruta
import com.google.firebase.firestore.FirebaseFirestore

class FrutasRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getFrutas(): List<Fruta> {
        return listOf(
            Fruta(1, "Manzana", 0xFFE57373.toInt(), "Una manzana roja deliciosa...", "Detalle largo...", R.drawable.manzana, 5, "Las manzanas flotan...", "Estados Unidos 🇺🇸"),
            Fruta(2, "Banana", 0xFFFFEB3B.toInt(), "Una banana amarilla...", "Detalle largo...", R.drawable.banana, 4, "Las bananas son...", "Ecuador 🇪🇨"),
            Fruta(3, "Naranja", 0xFFFF9800.toInt(), "Una naranja jugosa...", "Detalle largo...", R.drawable.naranjas, 5, "Las naranjas son...", "España 🇪🇸"),
            Fruta(4, "Fresa", 0xFFFF4081.toInt(), "Una fresa roja...", "Detalle largo...", R.drawable.fresas, 5, "Las fresas son...", "México 🇲🇽"),
            Fruta(5, "Uva", 0xFF9C27B0.toInt(), "Un racimo de uvas...", "Detalle largo...", R.drawable.uvas, 4, "Las uvas son...", "Italia 🇮🇹"),
            Fruta(6, "Piña", 0xFFFFA726.toInt(), "Una piña tropical...", "Detalle largo...", R.drawable.pinas, 5, "Las piñas pueden...", "Costa Rica 🇨🇷"),
            Fruta(7, "Mango", 0xFFFFA000.toInt(), "Un mango dulce...", "Detalle largo...", R.drawable.mangos, 5, "El mango es...", "India 🇮🇳"),
            Fruta(8, "Sandía", 0xFF4CAF50.toInt(), "Una sandía grande...", "Detalle largo...", R.drawable.sandias, 5, "La sandía está...", "Brasil 🇧🇷")
        )
    }

    fun guardarFrutasEnFirestore() {
        val frutas = getFrutas()
        for (fruta in frutas) {
            db.collection("frutas")
                .document(fruta.id.toString()) // Guardar con ID único
                .set(fruta)
                .addOnSuccessListener {
                    println("Fruta ${fruta.nombre} guardada exitosamente en Firestore")
                }
                .addOnFailureListener { e ->
                    println("Error al guardar ${fruta.nombre}: ${e.message}")
                }
        }
    }
}
