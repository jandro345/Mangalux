package com.example.probando_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.probando_1.utilidades.utilidades;

public class RegistroManga {



    public static void registrarManga(Context contexto, String campo_fuente,String campo_idioma, String campo_id, String campo_nombre, String campo_url, String campo_ult_cap,
                                String campo_cover){

        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(contexto,"Bdmanga",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(utilidades.CAMPO_IDIOMA,campo_idioma);
        values.put(utilidades.CAMPO_FUENTE,campo_fuente);
        values.put(utilidades.CAMPO_COVER,campo_cover);
        values.put(utilidades.CAMPO_ID,campo_id);
        values.put(utilidades.CAMPO_NOMBRE,campo_nombre);
        values.put(utilidades.CAMPO_URL,campo_url);
        values.put(utilidades.CAMPO_ULT_CAP,campo_ult_cap);

        Long idResultante= db.insert(utilidades.TABLA_MANGA,utilidades.CAMPO_ID,values);
    }
}
