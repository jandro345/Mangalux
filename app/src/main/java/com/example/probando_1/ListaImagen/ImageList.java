package com.example.probando_1.ListaImagen;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

public class ImageList {
    String im;
    String numero;
    int SizeX;
    int SizeY;
    int width;
    int height;
   Bitmap imagen;

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }


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

    public int getWidth() {
        return width;
    }

    public ImageList(String im, String numero, int sizeX, int sizeY, int height, int width, Bitmap img) {
        this.im = im;
        this.numero = numero;
        SizeX = sizeX;
        SizeY = sizeY;
        this.width = width;
        this.height = height;
        this.imagen=img;

    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
