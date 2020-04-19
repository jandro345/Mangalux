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

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
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
        SubsamplingScaleImageView image_cap;
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
            viewHolder.image_cap = (SubsamplingScaleImageView) convertView.findViewById(R.id.manga_imagen);

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


        viewHolder.image_cap.setImage(ImageSource.cachedBitmap(ex));
        // Return the completed view to render on screen
        return convertView;
    }

}


