package com.tuyp.newresto.View.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tuyp.newresto.Model.Order;
import com.tuyp.newresto.Model.SashimiModel;
import com.tuyp.newresto.R;
import com.tuyp.newresto.View.Adapter.SashimiAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SashimiFragment extends Fragment {
    RecyclerView recyclerView;
    List<SashimiModel> sashimiModels = new ArrayList<>();
    List<Order> orders = new ArrayList<>();
    SashimiAdapter sashimiAdapter;
    CardView popOrder;
    TextView qtyNumberText;
    SharedPreferences sharedPreferences1,storage;

    public SashimiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sashimi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        popOrder = getActivity().findViewById(R.id.popOrder);
        qtyNumberText = getActivity().findViewById(R.id.qtyNumberText);
        recyclerView = getActivity().findViewById(R.id.recSashimi);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        Gson gson = new Gson();

        storage = getActivity().getSharedPreferences("storage", Context.MODE_PRIVATE);
//        if (sharedPreferences1.getString("data-order",null)!= null){
//
////            Log.d("dataaaaaa","masuk tidak null atas");
//            String json = sharedPreferences1.getString("data-order",null);
//            Type type = new TypeToken<List<Order>>() {}.getType();
//            orders = (List<Order>) gson.fromJson(json,type);
//            Log.d("dataaaaaaaaaa","size 1 = "+orders.size());
//        }else {
//
//        }

        if (storage.getString("data-sashimi" , null) == null){
            Log.d("dataaaaa","sashimi null");
            sashimiModels.add(new SashimiModel("Maguro","Tuna","maguro",15000));
            sashimiModels.add(new SashimiModel("Salom Ikoro","Salom roe on sushi rice","roll1",15000));
            sashimiModels.add(new SashimiModel("Maguro 2","Tuna","maguro",15000));
            sashimiModels.add(new SashimiModel("Maguro","Tuna","maguro",15000));
            sashimiModels.add(new SashimiModel("Maguro","Tuna","maguro",15000));
            SharedPreferences.Editor storageEdit = storage.edit();
            String json = gson.toJson(sashimiModels);
            storageEdit.putString("data-sashimi",json);
            Log.d("dataaaaaa","json = "+json);
            storageEdit.apply();
            sashimiAdapter = new SashimiAdapter(getContext(),sashimiModels,this);
            recyclerView.setAdapter(sashimiAdapter);
            sashimiAdapter.notifyDataSetChanged();
            Log.d("dataaaaaa","size = "+sashimiModels.size());
        } else {
//            storage.edit().clear().apply();
//            Log.d("dataaaaa","sashimi tidak null");
            String json = storage.getString("data-sashimi",null);
            Log.d("dataaaaaa","json = "+json);
            Type type = new TypeToken<List<SashimiModel>>() {}.getType();
            sashimiModels = (List<SashimiModel>) gson.fromJson(json,type);
//            Log.d("dataaaaaa","size = "+sashimiModels.size());
            sashimiAdapter = new SashimiAdapter(getContext(),sashimiModels,this);
            recyclerView.setAdapter(sashimiAdapter);
            sashimiAdapter.notifyDataSetChanged();
        }
    }
}
