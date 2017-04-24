package com.example.filu.simplediceapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DieSidesFragment extends Fragment {
    private static final int SPAN_COUNT = 2;

    protected RecyclerView mRecyclerView;
    protected DieSidesAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected DieSide[] mDataset;


    public DieSidesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataset = DieSides.mDataset;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_die_sides, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.dieSidesRecyclerLayout);

        mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);


        mAdapter = new DieSidesAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }


}
