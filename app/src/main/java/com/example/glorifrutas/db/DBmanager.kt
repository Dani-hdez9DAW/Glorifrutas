package com.example.glorifrutas.db

object DBmanager {
    // tabla frutas
    const val TABLA_FRUTA = "frutas"
    const val FRUTA_ID = "_id"
    const val FRUTA_NOMBRE = "nombre"
    const val FRUTA_COLOR = "color"
    const val FRUTA_DESCRIPCION_CORTA = "descripcion_corta"
    const val FRUTA_DESCRIPCION_LARGA = "descripcion_larga"
    const val FRUTA_IMAGEN_RES_ID = "imagen_res_id"
    const val FRUTA_VALORACION = "valoracion"
    const val FRUTA_DATO_CURIOSO = "dato_curioso"
    const val FRUTA_LUGAR_COSECHA = "lugar_cosecha"

    const val TABLA_FRUTA_CREATE = """
        CREATE TABLE $TABLA_FRUTA (
            $FRUTA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $FRUTA_NOMBRE TEXT NOT NULL,
            $FRUTA_COLOR INTEGER NOT NULL,
            $FRUTA_DESCRIPCION_CORTA TEXT,
            $FRUTA_DESCRIPCION_LARGA TEXT,
            $FRUTA_IMAGEN_RES_ID INTEGER,
            $FRUTA_VALORACION INTEGER,
            $FRUTA_DATO_CURIOSO TEXT,
            $FRUTA_LUGAR_COSECHA TEXT
        );
    """

}