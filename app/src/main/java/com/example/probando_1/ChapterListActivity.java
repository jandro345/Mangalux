package com.example.probando_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

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

import java.util.ArrayList;

public class ChapterListActivity extends AppCompatActivity {
    ArrayList<ChapterList> ChapterModels;
    ListView listView;
    private static ChapterAdapter adapter;
    String Artista;
    String categorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String id = extras.getString("id");
            ChapterModels=EdenChapterList(id);
            android.os.SystemClock.sleep(100);
            actualizar();
        }else {
            Snackbar.make(findViewById(View.generateViewId()), "Fallo ", Snackbar.LENGTH_LONG)
                    .setAction("No action", null).show();


        }
        }



    protected ArrayList<ChapterList> EdenChapterList(String chapterURL) {
//Conexión por medio de Volley(una libreria de google)
        String url = "https://www.mangaeden.com/api/manga/" + chapterURL+"/";
        final ArrayList<ChapterList> Models = new ArrayList<ChapterList>();
        //Decimos de que tipo es el request que vamos a pedir
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    //
                    public void onResponse(JSONObject response) {
                        //Convertir JSON gigante en unos mas faciles de controlar
                        try {
                            //Evento de respuesta

                              JSONArray ind = (JSONArray) response.get("chapters");
                            for (int j = 0; j < ind.length(); j++) {
                                JSONArray aux =(JSONArray) ind.get(j);
                                    String numero=aux.get(0).toString();
                                    String titulo=(String) aux.get(2).toString();
                                    String url=(String) aux.get(3).toString();
                                    Models.add(new ChapterList(numero,url,titulo));

                            }
                            int a = 0;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    //Error en la respuesta
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    int j=0;
                    }
                });

        // Access the RequestQueue through your singleton class.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjectRequest);
        return Models;
    }


    protected void actualizar(){
        //Volvemos a hacer el proceso de encontrar nuestra lista,enlazarla con nuestro adaptador y meterle los mangas
        listView = (ListView) findViewById(R.id.ChapterListView);


        adapter = new ChapterAdapter(ChapterModels, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ChapterList dataModel = ChapterModels.get(position);
//Aquí deberiamos crear la nueva activity pasandole la URL de las imagenes se hace con bundle




            }
        });
    }

}
