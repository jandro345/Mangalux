package com.example.probando_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton busqueda=findViewById(R.id.busqueda);
        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada(v);
            }
        });
    }
    void entrada(View view){
        Intent Random;
        Random= new Intent(this,buscar.class);
        startActivity(Random);

    }
}
