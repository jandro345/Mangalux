package com.example.probando_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class buscar extends AppCompatActivity {
    ArrayList<MangaList> dataModels;
    ListView listView;
    ArrayList<JSONObject> MangaModels;
    SearchView buscar;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);


        MangaModels=EdenLista();
        this.buscar=findViewById(R.id.Buscador);
        buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dataModels = getDataModels(query);
                actualizar();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dataModels = getDataModels(newText);
                actualizar();
                return false;
            }
        });

    }

//Recibe la lista entera de mangas en JsonObjects
    protected ArrayList<JSONObject> EdenLista(){
//Conexión por medio de Volley(una libreria de google)
        String url = "https://www.mangaeden.com/api/list/0/";
       final ArrayList<JSONObject> Models=new ArrayList<JSONObject>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    //
                    public void onResponse(JSONObject response) {
                    //Convertir JSON gigante en unos mas faciles de controlar
                        try {
                           JSONArray ind= (JSONArray) response.get("manga");
                           for(int j=1;j<ind.length();j++){
                              JSONObject add=ind.getJSONObject(j);
                               Models.add(add);
                           }
                        int a=0;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Access the RequestQueue through your singleton class.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext()) ;
        queue.add(jsonObjectRequest) ;
        return Models;
    }
    //Devuelve el manga que coincide en la lista,busca en nuestro Array de mangas
    protected ArrayList<MangaList>  getDataModels(String titulo){
        final ArrayList<MangaList> Models=new ArrayList<MangaList>();
        for(int i=0;i<this.MangaModels.size();i++){
            try {
                //Se busca el manga por el titulo y se comprueban las coincidencias
                if(MangaModels.get(i).get("t").toString().toLowerCase().contains(titulo.toLowerCase())){
                    Models.add(new MangaList(MangaModels.get(i).get("t").toString().toLowerCase(),MangaModels.get(i).get("c").toString().toLowerCase(),R.drawable.linterna));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Models;
    }

    //Actualiza la lista de Mangas que se muestran
    protected void actualizar(){
        listView = (ListView) findViewById(R.id.list);


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