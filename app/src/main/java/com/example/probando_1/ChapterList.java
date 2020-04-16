package com.example.probando_1;

import android.text.Html;
import android.widget.ImageView;

public class ChapterList {
    String numero;
    ImageView logo;
    String url;
    String titulo;

    public ChapterList(String numero,String url,String titulo) {
        this.numero = numero;
        this.url=url;
        this.titulo=titulo;
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
