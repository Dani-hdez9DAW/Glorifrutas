package com.example.glorifrutas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {

    private static final String DB_NAME = "glorifrutas";
    private static final int DB_VERSION = 1;

    public Conexion(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBmanager.TABLA_EMPRESA_CREATE);
        sqLiteDatabase.execSQL(DBmanager.TABLA_FRUTA_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBmanager.TABLA_FRUTA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS empresas");
        onCreate(sqLiteDatabase);
    }
}