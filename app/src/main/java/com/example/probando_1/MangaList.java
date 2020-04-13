package com.example.probando_1;

import android.media.Image;

public class MangaList {
    String nombre;
    String autor;
    int cover;
    String url;
    String fuente;
    boolean favorito;

    public MangaList(String nombre, String autor, int cover) {
        this.nombre = nombre;
        this.autor = autor;
        this.cover = cover;
        this.url="N";
        this.favorito=true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }
}
