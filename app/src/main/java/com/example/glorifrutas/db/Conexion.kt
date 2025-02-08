package com.example.glorifrutas.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Conexion(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla de frutas
        db.execSQL(DBmanager.TABLA_FRUTA_CREATE)

        // Crear tabla de empresas si es necesaria (añádela aquí si es relevante)
        // db.execSQL(DBmanager.TABLA_EMPRESAS_CREATE)

        // Insertar datos iniciales (opcional para pruebas)
        db.execSQL("""
            INSERT INTO ${DBmanager.TABLA_FRUTA} 
            (${DBmanager.FRUTA_NOMBRE}, ${DBmanager.FRUTA_COLOR}, ${DBmanager.FRUTA_DESCRIPCION_CORTA}, 
            ${DBmanager.FRUTA_DESCRIPCION_LARGA}, ${DBmanager.FRUTA_IMAGEN_RES_ID}, ${DBmanager.FRUTA_VALORACION}, 
            ${DBmanager.FRUTA_DATO_CURIOSO}, ${DBmanager.FRUTA_LUGAR_COSECHA}) 
            VALUES ('Manzana', 0xFFFF0000, 'Fruta roja dulce', 'La manzana es deliciosa y crujiente.', 0, 5, 
            'Es buena para la salud.', 'Valle Central')
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Elimina las tablas existentes y vuelve a crearlas
        db.execSQL("DROP TABLE IF EXISTS ${DBmanager.TABLA_FRUTA}")
        // db.execSQL("DROP TABLE IF EXISTS empresas") // Si necesitas esta tabla, asegúrate de crearla en onCreate
        onCreate(db)
    }

    companion object {
        // Nombre de la base de datos
        private const val DB_NAME = "glorifrutas.db"
        // Versión de la base de datos
        private const val DB_VERSION = 1
    }
}
