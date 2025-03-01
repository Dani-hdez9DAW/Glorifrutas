package com.example.glorifrutas.utils

import com.example.glorifrutas.model.Fruta
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RealTimeFruits {
    private val databaseReference = Firebase.database.getReference("frutas")

    fun addFruit(fruit: Fruta) {
        val id = databaseReference.push().key
        id?.let {
            databaseReference.child(it).setValue(fruit)
        }
    }

    fun deleteFruit(fruitId: String) {
        databaseReference.child(fruitId).removeValue()
    }

    fun updateFruit(fruitId: String, updatedFruit: Fruta) {
        databaseReference.child(fruitId).setValue(updatedFruit)
    }

    fun getFruitsFlow(): Flow<List<Fruta>> {
        return callbackFlow {
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val fruits = snapshot.children.mapNotNull { it.getValue(Fruta::class.java) }
                    trySend(fruits).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            }
            databaseReference.addValueEventListener(valueEventListener)
            awaitClose {
                databaseReference.removeEventListener(valueEventListener)
            }
        }
    }
}