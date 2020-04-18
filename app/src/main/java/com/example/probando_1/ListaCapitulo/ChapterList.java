package com.example.probando_1.ListaCapitulo;

import android.widget.ImageView;

public class ChapterList {
    String numero;
    ImageView logo;
    String url;
    String titulo;
    String artista;
    String descripcion;

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ChapterList(String numero, String url, String titulo, String artista, String descripcion) {
        this.numero = numero;
        this.url = url;
        this.titulo = titulo;
        this.artista = artista;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ImageView getLogo() {
        return logo;
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }


}
