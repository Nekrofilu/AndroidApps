package com.example.filu.simplediceapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class DieSidesFragment extends Fragment {
    DieSidesAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    List<Drawable> images;
    int columnSpan;
    RecyclerView recyclerView;

    public DieSidesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_die_sides, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.dieSidesRecyclerLayout);
        mLayoutManager = new GridLayoutManager(getActivity(), columnSpan);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.scrollToPosition(0);

        mAdapter = new DieSidesAdapter(images);
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }


    public static DieSidesFragment newInstance(int columnSpan, List<Drawable> dieSides) {
        DieSidesFragment fragment = new DieSidesFragment();
        fragment.columnSpan = columnSpan;
        fragment.images = dieSides;
        return fragment;
    }
}
