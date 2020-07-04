package com.tuyp.newresto.View.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tuyp.newresto.Model.Order;
import com.tuyp.newresto.Model.SashimiModel;
import com.tuyp.newresto.R;
import com.tuyp.newresto.View.Fragment.SashimiFragment;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SashimiAdapter extends RecyclerView.Adapter<SashimiAdapter.SashimiViewHolder> {
    List<SashimiModel> sashimiModels;
    List<Order> orders = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    SashimiFragment sashimiFragment;

    public SashimiAdapter( Context context,List<SashimiModel> sashimiModels,SashimiFragment sashimiFragment) {
        this.sashimiModels = sashimiModels;
        this.context = context;
        this.sashimiFragment = sashimiFragment;
        this.layoutInflater = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public SashimiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.sashimi_item,parent,false);
        SashimiViewHolder holder = new SashimiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SashimiViewHolder holder, int position) {
        Locale localeID = new Locale("in","ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
        final Gson gson = new Gson();
        final SharedPreferences sharedPreferences = context.getSharedPreferences("order1",Context.MODE_PRIVATE);
        SharedPreferences storage = context.getSharedPreferences("storage",Context.MODE_PRIVATE);
        String storage1 = storage.getString("data-sashimi",null);
        Log.d("dataaaaa","isi storage = "+storage1);
        if (sharedPreferences.getString("data-order",null)!= null){
            Log.d("dataaaaaa","masuk tidak null atas");
            String json = sharedPreferences.getString("data-order",null);
            Type type = new TypeToken<List<Order>>() {}.getType();
            orders = (List<Order>) gson.fromJson(json,type);
//            Log.d("dataaaaaa","order = "+orders.get(0).getName());
            for (int i = 0; i<orders.size();i++){
                if (sashimiModels.get(position).getName().equals(orders.get(i).getName())){
                    holder.qtyText.setText(""+orders.get(i).getQty());
                }
            }

//            Log.d("dataaaaaaaaaa","size dalam share = "+orders.size());
        }else {

        }
        int resource = context.getResources().getIdentifier(sashimiModels.get(position).getImage(),"drawable",context.getPackageName());

        holder.imgFood.setImageResource(resource);

        holder.foodName.setText(sashimiModels.get(position).getName());
        holder.descFood.setText(sashimiModels.get(position).getDescName());
        holder.foodPrice.setText(format.format(sashimiModels.get(position).getPrice()).toString());
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty =0;
                int jumlah = Integer.parseInt(holder.qtyText.getText().toString());
                int hitung = jumlah+1;

                int price = sashimiModels.get(holder.getAdapterPosition()).getPrice() * hitung;
                holder.qtyText.setText(String.valueOf(hitung));
//                if (orders.size() > 0){
//
//                }else {
//                    orders.add(new Order(sashimiModels.get(holder.getAdapterPosition()).getName(),hitung,price));
//                    String json = gson.toJson(orders);
//                    SharedPreferences.Editor editorPref = sharedPreferences.edit();
//                    editorPref.putString("data-order1",json);
//                    editorPref.apply();
//                    String getData = sharedPreferences.getString("data-order1",null);
//                    Log.d("isi get","isi get = "+getData);
//                }


                if (orders.size() > 0){
                    for (int i=0;i<orders.size();i++){

                       if (orders.get(i).getName().equals(sashimiModels.get(holder.getAdapterPosition()).getName())){


                           orders.set(i,new Order(sashimiModels.get(holder.getAdapterPosition()).getName(),hitung,price));
                           String json = gson.toJson(orders);
                           SharedPreferences.Editor editorPref = sharedPreferences.edit();
                           editorPref.putString("data-order",json);
                           editorPref.apply();
                           break;
                       }else {
                           for (int j = 0;j<orders.size();j++){
                               if (sashimiModels.get(holder.getAdapterPosition()).getName().equals(orders.get(j).getName())){
//                                   Log.d("dataaaaaaaaaa","ada yg sama");
                                   orders.set(j,new Order(sashimiModels.get(holder.getAdapterPosition()).getName(),hitung,price));
                                   String json = gson.toJson(orders);
//                                   Log.d("dataaaaa","json dlm contain = "+json);
                                   SharedPreferences.Editor editorPref = sharedPreferences.edit();
                                   editorPref.putString("data-order",json);
                                   editorPref.apply();
                                   for (int k = 0;k<orders.size();k++){
                                       qty += orders.get(k).getQty();
//                                       Log.d("dataaaaaaa","total = "+qty);
                                   }
//                                   sashimiFragment.setVisPop(qty);
                                   return;
                               }
                           }

//                           Log.d("dataaaa","keluar for");
                           String json = gson.toJson(orders);
                           orders.add(new Order(sashimiModels.get(holder.getAdapterPosition()).getName(),hitung,price));
                           SharedPreferences.Editor editorPref = sharedPreferences.edit();
                           editorPref.putString("data-order",json);
                           editorPref.apply();
//                           String json = gson.toJson(orders);
//                           Log.d("dataaaaa","posisi = "+i);
//                           Log.d("dataaaaa","json tidak cointain = "+json);
                           break;
                       }
                    }
                    String getData = sharedPreferences.getString("data-order",null);
                    Log.d("isi get","isi get = "+getData);

//                    Log.d("dataaaaaaaaaa","size = "+orders.size());



                }else {
                    orders.add(new Order(sashimiModels.get(holder.getAdapterPosition()).getName(),hitung,price));
                    String json = gson.toJson(orders);
                    SharedPreferences.Editor editorPref = sharedPreferences.edit();
                    editorPref.putString("data-order",json);
                    editorPref.apply();
                    String getData = sharedPreferences.getString("data-order",null);
                    Log.d("isi get","isi get = "+getData);
                }
                for (int k = 0;k<orders.size();k++){

                    qty += orders.get(k).getQty();
                    int totalQty = 0;
                    totalQty = totalQty + qty;
                    Log.d("dataaaaaaa","total = "+qty);
                }
//                sashimiFragment.setVisPop(qty);
                String json = gson.toJson(orders);
                Log.d("dataaaaa","json d luar smua  = "+json);
                if (sharedPreferences.getString("data-order",null)!= null){
                    Log.d("dataaa","masuk share tidak null");
                    String jsonnew = gson.toJson(orders);
                    Log.d("dataaaaaa","json new = "+jsonnew);
                    SharedPreferences.Editor editornew = sharedPreferences.edit();
                    editornew.putString("data-order",jsonnew);
                    editornew.apply();
                }else {
                    Log.d("dataaa","masuk share  null");
                    SharedPreferences.Editor editorPref = sharedPreferences.edit();
                    editorPref.putString("data-order",json);
                    editorPref.apply();
                }








            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty =0;
                int jumlah = Integer.parseInt(holder.qtyText.getText().toString());
                String json = gson.toJson(orders);
                if (jumlah > 0){
                    int hitung = jumlah - 1;
                    holder.qtyText.setText(String.valueOf(hitung));
                    int price = sashimiModels.get(holder.getAdapterPosition()).getPrice() * hitung;
                    Log.d("dataaaaa","json d luar smua  = "+json);
                    if (orders.size() > 0){
                        for (int i=0;i<orders.size();i++){

                            if (orders.get(i).getName().equals(sashimiModels.get(holder.getAdapterPosition()).getName())){
                                Log.d("dataaaa","masuk contain");

                                orders.set(i,new Order(sashimiModels.get(holder.getAdapterPosition()).getName(),hitung,price));
                                json = gson.toJson(orders);
                                Log.d("dataaaaa","json dlm contain = "+json);

                                break;
                            }else {
                                for (int j = 0;j<orders.size();j++){
                                    if (sashimiModels.get(holder.getAdapterPosition()).getName().equals(orders.get(j).getName())){
                                        Log.d("dataaaaaaaaaa","ada yg sama");
                                        orders.set(j,new Order(sashimiModels.get(holder.getAdapterPosition()).getName(),hitung,price));
                                        json = gson.toJson(orders);
                                        Log.d("dataaaaa","json dlm contain = "+json);
                                        SharedPreferences.Editor editorPref = sharedPreferences.edit();
                                        editorPref.putString("data-order",json);
                                        editorPref.apply();
                                        for (int k = 0;k<orders.size();k++){

                                            qty += orders.get(k).getQty();
                                            int totalQty = 0;
                                            totalQty = totalQty + qty;
                                            Log.d("dataaaaaaa","total = "+qty);
                                        }
//                                        sashimiFragment.setVisPop(qty);
                                        return;
                                    }
                                }

                                Log.d("dataaaa","keluar for");
                                orders.add(new Order(sashimiModels.get(holder.getAdapterPosition()).getName(),hitung,price));
                                json = gson.toJson(orders);
                                Log.d("dataaaaa","posisi = "+i);
                                Log.d("dataaaaa","json tidak cointain = "+json);
                                break;
                            }
                        }

                        Log.d("dataaaaaaaaaa","size = "+orders.size());



                    }else {
                        orders.add(new Order(sashimiModels.get(holder.getAdapterPosition()).getName(),hitung,price));
                        json = gson.toJson(orders);
                        Log.d("dataaaaa","json > 0 = "+json);
                    }
                    for (int k = 0;k<orders.size();k++){

                        qty += orders.get(k).getQty();
                        int totalQty = 0;
                        totalQty = totalQty + qty;
                        Log.d("dataaaaaaa","total = "+qty);
                    }
//                    sashimiFragment.setVisPop(qty);
                    json = gson.toJson(orders);
                    Log.d("dataaaaa","json d luar smua  = "+json);
                    if (sharedPreferences.getString("data-order",null)!= null){
                        Log.d("dataaa","masuk share tidak null");
                        String jsonnew = gson.toJson(orders);
                        SharedPreferences.Editor editornew = sharedPreferences.edit();
                        editornew.putString("data-order",jsonnew);
                        editornew.apply();
                    }else {
                        Log.d("dataaa","masuk share  null");
                        SharedPreferences.Editor editorPref = sharedPreferences.edit();
                        editorPref.putString("data-order",json);
                        editorPref.apply();
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return sashimiModels.size();
    }

    public class SashimiViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView foodName,descFood,foodPrice,qtyText;
        Button btnMinus,btnPlus;
        public SashimiViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            foodName = itemView.findViewById(R.id.foodName);
            descFood = itemView.findViewById(R.id.descFood);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            qtyText = itemView.findViewById(R.id.qtyText);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
        }
    }
}
