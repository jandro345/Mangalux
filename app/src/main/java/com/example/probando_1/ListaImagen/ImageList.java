package com.example.probando_1.ListaImagen;

public class ImageList {
    String im;
    String numero;
    int SizeX;
    int SizeY;

    public int getSizeX() {
        return SizeX;
    }

    public void setSizeX(int sizeX) {
        SizeX = sizeX;
    }

    public int getSizeY() {
        return SizeY;
    }

    public void setSizeY(int sizeY) {
        SizeY = sizeY;
    }

    public ImageList(String im, String numero,int sizeX,int sizeY) {
        this.im = im;
        this.numero = numero;
        SizeX = sizeX;
        SizeY = sizeY;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ImageList(String im, String numero) {
        this.im = im;
        this.numero = numero;

    }
}
