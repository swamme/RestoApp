package com.tuyp.newresto.View.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuyp.newresto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SushiFragment extends Fragment {


    public SushiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sushi, container, false);
    }

}
