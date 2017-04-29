package com.example.filu.simplediceapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by filu on 24.04.17.
 */

public class DieSidesAdapter extends RecyclerView.Adapter<DieSidesAdapter.ViewHolder> {
    DieSide[] mDataset;
    Context ctx;


    public DieSidesAdapter(Context ctx) {
        this.mDataset = DieSides.mDataset;
        this.ctx = ctx;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_row_item, parent, false);
        return new ViewHolder(v);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.row_img);
            imageView.setImageDrawable(ContextCompat.getDrawable(ctx.getApplicationContext(),R.drawable.die1));
        }

        public ImageView getImageView() {
            return imageView;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.imageView.setImageDrawable(ContextCompat.getDrawable(ctx.getApplicationContext(), mDataset[position].getSourceId()));

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }




}
