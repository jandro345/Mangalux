package com.example.probando_1.entidades;

public class Bdmanga {
    //Definición de parámetros
    private Integer id;
    private Integer nombre;
    private Integer ult_cap;
    private Integer url;
    private Integer cover;
    private Integer fuente;
    private Integer idioma;

    public Bdmanga(Integer id, Integer nombre, Integer ult_cap, Integer url, Integer cover, Integer fuente, Integer idioma) {
        this.id = id;
        this.nombre = nombre;
        this.ult_cap = ult_cap;
        this.url = url;
        this.cover = cover;
        this.fuente = fuente;
        this.idioma = idioma;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Integer getUlt_cap() {
        return ult_cap;
    }

    public void setUlt_cap(Integer ult_cap) {
        this.ult_cap = ult_cap;
    }

    public Integer getUrl() {
        return url;
    }

    public void setUrl(Integer url) {
        this.url = url;
    }

    public Integer getCover() {
        return cover;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }

    public Integer getFuente() {
        return fuente;
    }

    public void setFuente(Integer fuente) {
        this.fuente = fuente;
    }

    public Integer getIdioma() {
        return idioma;
    }

    public void setIdioma(Integer idioma) {
        this.idioma = idioma;
    }
}

