package com.example.probando_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.probando_1.ListaManga.CustomAdapter;
import com.example.probando_1.ListaManga.MangaList;
import com.example.probando_1.ListaManga.buscar;

import java.util.ArrayList;

public class historial extends AppCompatActivity {
    ArrayList<MangaList> dataModels;
    ListView listView;
    SearchView buscar;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_manga",null,1);
        ImageView cov=new ImageView(this); //Bugea por ahora,he puesto una de prueba mientras se arregla
        cov.setImageResource(R.drawable.inicio);
        dataModels.add(new MangaList("Berserk","7",cov,"2","1233324","3"));
        actualizar();
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
        Random= new Intent(this, buscar.class);
        startActivity(Random);
    }
    //Función que salta a la actividad de inicio
    void entrada3(View view){
        Intent Random;
        Random= new Intent(this,MainActivity.class);
        startActivity(Random);

    }
    protected void actualizar(){
        //Volvemos a hacer el proceso de encontrar nuestra lista,enlazarla con nuestro adaptador y meterle los mangas
        listView = (ListView) findViewById(R.id.list);


        adapter = new CustomAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MangaList dataModel = dataModels.get(position);
//Aquí deberiamos crear la nueva activity pasandole la URL del MangaList,se hace con bundle
            }
        });
    }
}
