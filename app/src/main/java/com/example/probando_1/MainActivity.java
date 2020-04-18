package com.example.probando_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.probando_1.ListaManga.buscar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Botón de búsqueda
        ImageButton busqueda=findViewById(R.id.busqueda);
        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada2(v);
            }
        });

        //Botón de Historial
        ImageButton historial=findViewById(R.id.historial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada1(v);
            }
        });

    }


    //Función que salta a la actividad de buscar
    void entrada2(View view){
        Intent Random;
        Random= new Intent(this, buscar.class);
        startActivity(Random);
    }
    //Función que salta a la actividad de historial
    void entrada1(View view){
        Intent Random;
        Random= new Intent(this,historial.class);
        startActivity(Random);

    }
}
