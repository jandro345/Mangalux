package com.example.probando_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ChapterAdapter extends ArrayAdapter<ChapterList> implements View.OnClickListener{


    private ArrayList<ChapterList> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtChapter;
        ImageView logo;
    }

    public ChapterAdapter(ArrayList<ChapterList> data, Context context) {
        super(context, R.layout.lista_manga, data);
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
        ChapterList dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ChapterAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ChapterAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lista_manga, parent, false);
            viewHolder.txtChapter = (TextView) convertView.findViewById(R.id.txtChapter);
            viewHolder.logo = (ImageView) convertView.findViewById(R.id.logo);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChapterAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtChapter.setText(dataModel.getNumero());
        viewHolder.logo.setOnClickListener(this);
        viewHolder.logo.setImageResource(R.drawable.inicio);
        viewHolder.logo.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}

