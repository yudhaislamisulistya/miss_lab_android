package com.leadevs.misslab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.leadevs.misslab.R;
import com.leadevs.misslab.models.Asisten;

import java.util.List;


public class AsistenAdapter extends RecyclerView.Adapter<AsistenAdapter.MyViewHolder> {

    Context context ;
    List<Asisten> mData;


    public AsistenAdapter(Context context, List<Asisten> mData) {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_asisten,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.TVNamaLengkap.setText(mData.get(i).getFullname());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView TVNamaLengkap;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            TVNamaLengkap = itemView.findViewById(R.id.item_asisten_namalengkap);
        }
    }
}
