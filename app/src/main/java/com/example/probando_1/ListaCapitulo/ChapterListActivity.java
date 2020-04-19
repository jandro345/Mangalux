package com.example.probando_1.ListaCapitulo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.probando_1.ConexionSQLiteHelper;
import com.example.probando_1.ListaCapitulo.ChapterAdapter;
import com.example.probando_1.ListaCapitulo.ChapterList;
import com.example.probando_1.ListaImagen.ImagenScrollActivity;
import com.example.probando_1.R;
import com.example.probando_1.utilidades.utilidades;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChapterListActivity extends AppCompatActivity {
    ArrayList<ChapterList> ChapterModels;
    ListView listView;
    private static ChapterAdapter adapter;
    String id2;
    String cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id2 = extras.getString("id");
            cover=extras.getString("cover");
            //Buscamos los View del layout para poner los datos
            ImageView portada=(ImageView) findViewById(R.id.portada);
            //Cargamos la imagen de la url del path en el ImageView portada

            Picasso.get().load("https://cdn.mangaeden.com/mangasimg/"+cover).into(portada);
            //Ponemos la descripcion del manga


            int i=0;
            ChapterModels=EdenChapterList(id2);
            //Cuando nos responda el servidor tendremos ya capitulos y el tamaño no será 0 por lo tanto actualizamos los capitulos.




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
                              String info=(String)response.get("description");
                              String artist=(String)response.get("artist");
                            for (int j = 0; j < ind.length(); j++) {
                                JSONArray aux =(JSONArray) ind.get(j);
                                    String numero=aux.get(0).toString();
                                    String titulo=(String) aux.get(2).toString();
                                    String url=(String) aux.get(3).toString();
                                    Models.add(new ChapterList(numero,url,titulo,artist,info));

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


    protected void actualizar(ArrayList<ChapterList> ChapterModel){
        //Volvemos a hacer el proceso de encontrar nuestra lista,enlazarla con nuestro adaptador y meterle los mangas
        listView = (ListView) findViewById(R.id.ChapterListView);
        TextView info=(TextView) findViewById(R.id.descripcion);

        info.setText(String.format("%s\n%s", ChapterModel.get(0).getArtista(), ChapterModel.get(0).getDescripcion()));
        adapter = new ChapterAdapter(ChapterModel, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ChapterList dataModel = ChapterModels.get(position);

                registrarManga(getApplicationContext(),"MangaEden","INGLES",id2,
                        dataModel.getTitulo(),dataModel.getUrl(),dataModel.getNumero(),cover);
//Aquí deberiamos crear la nueva activity pasandole la URL de las imagenes se hace con bundle
                GoToLector(view,dataModel.getUrl());




            }
        });
    }

   public static void registrarManga(Context contexto, String campo_fuente, String campo_idioma, String campo_id, String campo_nombre, String campo_url, String campo_ult_cap,
                        String campo_cover) {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(contexto, "Bdmanga", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(utilidades.CAMPO_IDIOMA, campo_idioma);
        values.put(utilidades.CAMPO_FUENTE, campo_fuente);
        values.put(utilidades.CAMPO_COVER, campo_cover);
        values.put(utilidades.CAMPO_ID, campo_id);
        values.put(utilidades.CAMPO_NOMBRE, campo_nombre);
        values.put(utilidades.CAMPO_URL, campo_url);
        values.put(utilidades.CAMPO_ULT_CAP, campo_ult_cap);

        Long idResultante = db.insert(utilidades.TABLA_MANGA, utilidades.CAMPO_ID, values);
        Toast.makeText(contexto,"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
    }
    void GoToLector(View view,String id){
        Intent Random;
        Random= new Intent(this, ImagenScrollActivity.class);
        Random.putExtra("id",id);
        startActivity(Random);

    }
}
