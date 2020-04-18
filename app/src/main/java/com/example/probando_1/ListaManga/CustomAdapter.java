package com.example.probando_1.ListaManga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.probando_1.ListaManga.MangaList;
import com.example.probando_1.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<MangaList> implements View.OnClickListener{

    private ArrayList<MangaList> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtNombre;
        TextView txtAutor;
        ImageView cover;
    }

    public CustomAdapter(ArrayList<MangaList> data, Context context) {
        super(context, R.layout.lista_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        MangaList dataModel=(MangaList) object;

        switch (v.getId())
        {
            case R.id.logo:
                Snackbar.make(v, "Release date " +dataModel.getNombre(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;


    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MangaList dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lista_item, parent, false);
            viewHolder.txtNombre = (TextView) convertView.findViewById(R.id.Nombre);
            viewHolder.txtAutor = (TextView) convertView.findViewById(R.id.txtChapter);
            viewHolder.cover = (ImageView) convertView.findViewById(R.id.logo);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtNombre.setText(dataModel.getNombre());
        viewHolder.txtAutor.setText(dataModel.getAutor());
        viewHolder.cover.setOnClickListener(this);
        if(dataModel.getIm()!="null")
        Picasso.get().load("https://cdn.mangaeden.com/mangasimg/"+dataModel.getIm()).into(viewHolder.cover);
        else
            viewHolder.cover.setImageResource(R.drawable.inicio);
        viewHolder.cover.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}