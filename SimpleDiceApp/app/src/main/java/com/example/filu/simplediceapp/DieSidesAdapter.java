package com.example.filu.simplediceapp;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by filu on 24.04.17.
 */

public class DieSidesAdapter extends RecyclerView.Adapter<DieSidesAdapter.ViewHolder> {
    List<Drawable> sides;

    public DieSidesAdapter(List<Drawable> sides) {
        this.sides = sides;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_row_item, parent, false);
        return new ViewHolder(v);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.row_img);
            img.setImageDrawable(sides.get(0));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.img.setImageDrawable(sides.get(position));
    }

    @Override
    public int getItemCount() {
        return sides.size();
    }




}
