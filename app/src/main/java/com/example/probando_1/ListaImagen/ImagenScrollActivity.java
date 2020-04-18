package com.example.probando_1.ListaImagen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.probando_1.ListaCapitulo.ChapterAdapter;
import com.example.probando_1.ListaCapitulo.ChapterList;
import com.example.probando_1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImagenScrollActivity extends AppCompatActivity {

    ArrayList<ImageList> ImageModels;
    ListView listView;
    private static ImagenAdapter adapter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_scroll);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id=extras.getString("id");
            ImageModels=EdenChapterList(id);
        }
    }


    protected ArrayList<ImageList> EdenChapterList(String chapterURL) {
//Conexión por medio de Volley(una libreria de google)
        String url = "https://www.mangaeden.com/api/chapter/" + chapterURL+"/";
        final ArrayList<ImageList> Models = new ArrayList<ImageList>();
        //Decimos de que tipo es el request que vamos a pedir
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    //
                    public void onResponse(JSONObject response) {
                        //Convertir JSON gigante en unos mas faciles de controlar
                        try {
                            //Evento de respuesta

                            JSONArray ind = (JSONArray) response.get("images");
                            for (int j = 0; j < ind.length(); j++) {
                                JSONArray aux =(JSONArray) ind.get(ind.length()-j-1);
                                String numero=aux.get(0).toString();
                                String im=aux.get(1).toString();
                                int SizeX=(int) aux.get(2);
                                int SizeY=(int) aux.get(3);


                                Models.add(new ImageList(im,numero,SizeX,SizeY));

                            }
                            actualizar(Models);
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
    protected void actualizar(ArrayList<ImageList> ChapterModel){
        //Volvemos a hacer el proceso de encontrar nuestra lista,enlazarla con nuestro adaptador y meterle los mangas
        listView = (ListView) findViewById(R.id.listaImagen);
        adapter = new ImagenAdapter(ChapterModel, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageList dataModel = ImageModels.get(position);


            }
        });
    }
}
