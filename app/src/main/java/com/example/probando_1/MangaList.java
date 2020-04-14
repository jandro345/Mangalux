package com.example.probando_1;

import android.media.Image;
import android.widget.ImageView;

public class MangaList {
    String nombre;
    String autor;
    ImageView cover;
    String url;
    String fuente;

    public MangaList(String nombre, String autor, ImageView cover,String url,String fuente) {
        this.nombre = nombre;
        this.autor = autor;
        this.cover = cover;
        this.url=url;
        this.fuente=fuente;
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

    public ImageView getCover() {
        return cover;
    }

    public void setCover(ImageView cover) {
        this.cover = cover;
    }
}
