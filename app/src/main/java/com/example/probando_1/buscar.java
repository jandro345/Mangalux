package com.example.probando_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class buscar extends AppCompatActivity {
    ArrayList<MangaList> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        listView = (ListView) findViewById(R.id.list);

        dataModels = new ArrayList<>();

        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));
        dataModels.add(new MangaList("Berserk", "Kentaro Miura", R.drawable.linterna));


        adapter = new CustomAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MangaList dataModel = dataModels.get(position);

                Snackbar.make(view, dataModel.getNombre() + "\n" + dataModel.getAutor(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();


            }
        });
    }

}