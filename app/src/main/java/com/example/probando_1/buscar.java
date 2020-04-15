package com.example.probando_1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
            //Event listener si se da al enter en la busqueda
            public boolean onQueryTextSubmit(String query) {
                //Comprobamos que no sea 0 lo que hay para no actualizar

                if(query.length()!=0) {
                    dataModels = getDataModels(query);
                    actualizar();
                }
                return false;
            }

            //Event listener si cambia el texto en el buscador
            @Override
            public boolean onQueryTextChange(String newText) {
                //Idem
                if(newText.length()!=0) {
                    dataModels = getDataModels(newText);
                    actualizar();
                }
                return false;
            }
        });
        //Botón de Inicio
        ImageButton inicio=findViewById(R.id.inicio);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada3(v);
            }
        });
        //Botón de Historial
        ImageButton historial=findViewById(R.id.historial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada2(v);
            }
        });

    }

//Recibe la lista entera de mangas en JsonObjects
    protected ArrayList<JSONObject> EdenLista(){
//Conexión por medio de Volley(una libreria de google)
        String url = "https://www.mangaeden.com/api/list/0/";
       final ArrayList<JSONObject> Models=new ArrayList<JSONObject>();
       //Decimos de que tipo es el request que vamos a pedir
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    //
                    public void onResponse(JSONObject response) {
                    //Convertir JSON gigante en unos mas faciles de controlar
                        try {
                            //Evento de respuesta
                           
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
                    //Error en la respuesta
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

                    //Sacamos el valor que corresponde a la variable correspondiente del JSON
                    String tit=MangaModels.get(i).get("t").toString().toLowerCase(); //t:titulo
                    String cat=MangaModels.get(i).get("c").toString().toLowerCase();//c:categoria
                    String im=MangaModels.get(i).get("im").toString();
                    String id=MangaModels.get(i).get("i").toString().toLowerCase(); //id:id del manga
                    ImageView cov=new ImageView(this); //Bugea por ahora,he puesto una de prueba mientras se arregla
                    cov.setImageResource(R.drawable.inicio);
                    Models.add(new MangaList(tit,cat,cov,id,"Eden",im)); //Añadimos el manga con su constructor
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Models;
    }

    //Actualiza la lista de Mangas que se muestran
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


                //Pequeña barra para comprobar que los datos estan bien sacados
                Snackbar.make(view, dataModel.getNombre() + "\n" + dataModel.getAutor(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();


            }
        });
    }

    //Conectamos con la pagina y sacamos la imagen,peta y el servidor no nos deja entrar,supongo que por la cantidad de peticiones
    //Tambien puede ser porque imageRequest esta decrypted(en desuso) y por eso peta
    protected ImageView getCoverEden(String img){

        //Mismo proceso pero con una imagen
       final ImageView cover=new ImageView(this);
       String url="https://cdn.mangaeden.com/mangasimg/"+img;


        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        cover.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        cover.setImageResource(R.drawable.inicio);
                    }
                });

        //Ponemos nuestra peticion en la cola para que se ejecute
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext()) ;
        queue.add(request) ;


        return cover;
    }
    //Función que salta a la actividad de búsqueda
    void entrada2(View view){
        Intent Random;
        Random= new Intent(this,buscar.class);
        startActivity(Random);
    }
    void entrada3(View view){
        Intent Random;
        Random= new Intent(this,MainActivity.class);
        startActivity(Random);

    }
}