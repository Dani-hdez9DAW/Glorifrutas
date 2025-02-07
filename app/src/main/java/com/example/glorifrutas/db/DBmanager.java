package com.example.glorifrutas.db;

public class DBmanager {
    // tabla frutas
    public static final String TABLA_FRUTA = "frutas";
    public static final String FRUTA_ID = "_id";
    public static final String FRUTA_NOMBRE = "nombre";
    public static final String FRUTA_COLOR = "color";
    public static final String FRUTA_DESCRIPCION_CORTA = "descripcion_corta";
    public static final String FRUTA_DESCRIPCION_LARGA = "descripcion_larga";
    public static final String FRUTA_IMAGEN_RES_ID = "imagen_res_id";
    public static final String FRUTA_VALORACION = "valoracion";
    public static final String FRUTA_DATO_CURIOSO = "dato_curioso";
    public static final String FRUTA_LUGAR_COSECHA = "lugar_cosecha";

    public static final String TABLA_FRUTA_CREATE = "CREATE TABLE " + TABLA_FRUTA + " (" +
            FRUTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FRUTA_NOMBRE + " TEXT NOT NULL, " +
            FRUTA_COLOR + " INTEGER NOT NULL, " +
            FRUTA_DESCRIPCION_CORTA + " TEXT, " +
            FRUTA_DESCRIPCION_LARGA + " TEXT, " +
            FRUTA_IMAGEN_RES_ID + " INTEGER, " +
            FRUTA_VALORACION + " INTEGER, " +
            FRUTA_DATO_CURIOSO + " TEXT, " +
            FRUTA_LUGAR_COSECHA + " TEXT);";

    // tabla empresas
    public static final String TABLA_EMPRESA_CREATE = "CREATE TABLE empresas(_id INTEGER NOT NULL, nombre TEXT NOT NULL);";
}