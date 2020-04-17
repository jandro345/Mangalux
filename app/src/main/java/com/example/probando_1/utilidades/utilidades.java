package com.example.probando_1.utilidades;

public class utilidades {

    //Constantes tabla infmanga
    public static final String TABLA_MANGA="manga";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_URL="url";
    public static final String CAMPO_ULT_CAP="ult_cap";
    public static final String CAMPO_FUENTE="fuente";
    public static final String CAMPO_IDIOMA="idioma";
    public static final String CAMPO_COVER="cover";

    public static final String CREAR_TABLA_MANGA="CREATE TABLE "+TABLA_MANGA+"("+CAMPO_URL+" TEXT ,"+CAMPO_ID+" TEXT," +
            " "+CAMPO_NOMBRE+" TEXT, "+CAMPO_ULT_CAP+" TEXT,"+CAMPO_FUENTE+" TEXT,"+CAMPO_IDIOMA+" TEXT,"+CAMPO_COVER+" TEXT)";
}
