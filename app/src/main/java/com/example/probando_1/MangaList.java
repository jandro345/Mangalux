package com.example.probando_1;

import android.media.Image;
import android.widget.ImageView;

public class MangaList {
    String nombre;
    String autor;
    ImageView cover;
    String id;
    String fuente;

    public MangaList(String nombre, String autor, ImageView cover,String id,String fuente) {
        this.nombre = nombre;
        this.autor = autor;
        this.cover = cover;
        this.id=id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
}
