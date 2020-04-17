package com.example.probando_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.probando_1.utilidades.utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    //Creación y definición del tipo de parámetros que se va a crear dentro de la tabla


    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override //Acción de crear
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utilidades.CREAR_TABLA_MANGA);
    }

    @Override //Acción de actualizar
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS infManga");
    onCreate(db);
    }
}
