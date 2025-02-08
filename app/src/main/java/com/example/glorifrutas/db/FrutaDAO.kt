package com.example.glorifrutas.db

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.glorifrutas.model.Fruta

class FrutaDAO(private val db: SQLiteDatabase) {

    fun getAllFrutas(): List<Fruta> {
        val frutas = mutableListOf<Fruta>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DBmanager.TABLA_FRUTA}", null)
        if (cursor.moveToFirst()) {
            do {
                val fruta = Fruta(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(DBmanager.FRUTA_ID)),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow(DBmanager.FRUTA_NOMBRE)),
                    colorResId = cursor.getInt(cursor.getColumnIndexOrThrow(DBmanager.FRUTA_COLOR)),
                    descripcionCorta = cursor.getString(cursor.getColumnIndexOrThrow(DBmanager.FRUTA_DESCRIPCION_CORTA)),
                    descripcionLarga = cursor.getString(cursor.getColumnIndexOrThrow(DBmanager.FRUTA_DESCRIPCION_LARGA)),
                    imagenResId = cursor.getInt(cursor.getColumnIndexOrThrow(DBmanager.FRUTA_IMAGEN_RES_ID)),
                    valoracion = cursor.getInt(cursor.getColumnIndexOrThrow(DBmanager.FRUTA_VALORACION)),
                    datoCurioso = cursor.getString(cursor.getColumnIndexOrThrow(DBmanager.FRUTA_DATO_CURIOSO)),
                    lugarCosecha = cursor.getString(cursor.getColumnIndexOrThrow(DBmanager.FRUTA_LUGAR_COSECHA))
                )
                frutas.add(fruta)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return frutas
    }
}
