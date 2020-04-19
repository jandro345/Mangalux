package com.example.probando_1.ListaImagen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.example.probando_1.ListaCapitulo.ChapterAdapter;
import com.example.probando_1.ListaCapitulo.ChapterList;
import com.example.probando_1.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ImagenScrollActivity extends AppCompatActivity {

    ArrayList<ImageList> ImageModels;
    ListView listView;
    private static ImagenAdapter adapter;
    String id;
      int width;
   int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_scroll);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            height = extras.getInt("height");
            width =extras.getInt("width");
            ImageModels = EdenChapterList(id, height, width);
        }
    }


    protected ArrayList<ImageList> EdenChapterList(String chapterURL, final int height, final int width) {
//Conexión por medio de Volley(una libreria de google)
        final String url = "https://www.mangaeden.com/api/chapter/" + chapterURL + "/";
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
                            final JSONArray ind = (JSONArray) response.get("images");

                            Thread thread = new Thread(new Runnable() {


                                @Override
                                public void run() {
                                    try {
                                        for (int j = 0; j < ind.length(); j++) {

                                            JSONArray aux = (JSONArray) ind.get(ind.length() - j - 1);
                                            final String numero = aux.get(0).toString();
                                            final String im = aux.get(1).toString();
                                            final int SizeX = (int) aux.get(2);
                                            final int SizeY = (int) aux.get(3);
                                            final Bitmap[] map = new Bitmap[1];
                                            Bitmap ex=null;
                                            Bitmap pool=null;

                                            ex=Glide.with(getApplicationContext()).asBitmap().load("https://cdn.mangaeden.com/mangasimg/" + im).override(width, height).submit().get();


                                            ImageModels.add(new ImageList(im, numero, SizeX, SizeY, height, width,ex));
                                        }
                                        actualizar();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            thread.start();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    //Error en la respuesta
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        int j = 0;
                    }
                });

        // Access the RequestQueue through your singleton class.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjectRequest);
        return Models;
    }

    protected void actualizar() {

        runOnUiThread(new Runnable() {
            public void run() {


                //Volvemos a hacer el proceso de encontrar nuestra lista,enlazarla con nuestro adaptador y meterle los mangas
                listView = (ListView) findViewById(R.id.listaImagen);
                adapter = new ImagenAdapter(ImageModels, getApplicationContext());

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        ImageList dataModel = ImageModels.get(position);


                    }
                });


            }

        });


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        try {
           ReshapeAllBitmaps();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "PORTRAIT", Toast.LENGTH_LONG).show();
            //add your code what you want to do when screen on PORTRAIT MODE
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_LONG).show();
            //add your code what you want to do when screen on LANDSCAPE MODE
        }
    }

    public void ReshapeAllBitmaps() throws ExecutionException, InterruptedException {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        //Sacamos la resolucion actual del sistema
        final int new_height = displayMetrics.heightPixels;
        final int new_width = displayMetrics.widthPixels;

        Thread thread = new Thread(new Runnable() {


            @Override
            public void run() {


                //Vamos a hacer que mantenga el aspecto lo maximo posible
                for (int i = 0; i < ImageModels.size(); i++) {

                    ImageList dataModel=ImageModels.get(i);
                    float scale = new_width / dataModel.getSizeX();
                    int escalador = Math.round(scale);


                    //Si estamos con el movil girado,la altura sera menor a la de la imagen y no queremos que se vea achatado
                    //Ajustado con escala conseguimos una relacion y fijando los lados y haciendo variable la altura(muy feo pero que le hacemos)
                    ImageList ex=ImageModels.get(i);
                    String im=ex.getIm();
                    try {
                        ex.setImagen(Glide.with(getApplicationContext()).asBitmap().load("https://cdn.mangaeden.com/mangasimg/" +im).override(new_width, new_height).submit().get());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }
        });
       // thread.start();
    }

}
