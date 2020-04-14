package com.example.probando_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class historial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        //Botón de Búsqueda
        ImageButton busqueda=findViewById(R.id.busqueda);
        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada2(v);
            }
        });

        //Botón de Historial
        ImageButton historial=findViewById(R.id.inicio);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada3(v);
            }
        });


    }
    //Función que salta a la actividad de búsqueda
    void entrada2(View view){
        Intent Random;
        Random= new Intent(this,buscar.class);
        startActivity(Random);
    }
    //Función que salta a la actividad de inicio
    void entrada3(View view){
        Intent Random;
        Random= new Intent(this,MainActivity.class);
        startActivity(Random);

    }
}
