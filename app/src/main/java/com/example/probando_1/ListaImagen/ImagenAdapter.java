package com.example.probando_1.ListaImagen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.probando_1.ListaCapitulo.ChapterAdapter;
import com.example.probando_1.ListaCapitulo.ChapterList;
import com.example.probando_1.ListaManga.MangaList;
import com.example.probando_1.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImagenAdapter extends ArrayAdapter<ImageList> implements View.OnClickListener {
    private ArrayList<ImageList> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView image_cap;
    }

    public ImagenAdapter(ArrayList<ImageList> data, Context context) {
        super(context, R.layout.lista_imagen, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        ImageList dataModel = (ImageList) object;


    }

    private int lastPosition = -1;


    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ImageList dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ImagenAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ImagenAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lista_imagen, parent, false);
            viewHolder.image_cap = (ImageView) convertView.findViewById(R.id.manga_imagen);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ImagenAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        //Descargo la imagen y la ajusto al tamaño de mi pantalla para evitar problemas
        Bitmap ex = dataModel.getImagen();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        //Sacamos la resolucion actual del sistema
        int height= displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Bitmap tr;
        //Vamos a hacer que mantenga el aspecto lo maximo posible
        float scale=width/dataModel.getSizeX();
        int escalador= Math.round(scale);
        //Si estamos con el movil girado,la altura sera menor a la de la imagen y no queremos que se vea achatado
        //Ajustado con escala conseguimos una relacion y fijando los lados y haciendo variable la altura(muy feo pero que le hacemos)
        if(height<dataModel.getSizeY()) {

            tr = Bitmap.createScaledBitmap(ex, width, height*escalador, true);
        }
        else {
            tr = Bitmap.createScaledBitmap(ex, width, height, true);
        }
        viewHolder.image_cap.setImageBitmap(tr);
        // Return the completed view to render on screen
        return convertView;
    }

}


