package com.example.probando_1.ListaManga;

import android.media.Image;
import android.widget.ImageView;

public class MangaList {
    //Hay mas parametros obtenidos por la API,pero a este nivel no son utiles
    String nombre;
    String autor;
    ImageView cover;
    String id;
    String fuente;
    String im;
    //Constructor principal,seria recomendable crear uno momentaneamente pero sin el cover,asi cuando la persona haya dejado
    //de escribir,asignamos con el setter y evitamos hacer tantas peticiones al servidor. La idea esta debe implementarse en el ImagenesScroll.java
    public MangaList(String nombre, String autor, ImageView cover,String id,String fuente,String im) {
        this.nombre = nombre;
        this.autor = autor;
        this.cover = cover;
        this.id=id;
        this.fuente=fuente;
        this.im=im;
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

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
}
