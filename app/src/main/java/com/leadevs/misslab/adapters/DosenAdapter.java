package com.leadevs.misslab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.leadevs.misslab.R;
import com.leadevs.misslab.models.Dosen;

import java.util.List;


public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.MyViewHolder> {

    Context context ;
    List<Dosen> mData;


    public DosenAdapter(Context context, List<Dosen> mData) {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dosen,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.TVNamaLengkap.setText(mData.get(i).getNamaLengkap());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView TVNamaLengkap;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            TVNamaLengkap = itemView.findViewById(R.id.item_dosen_namalengkap);
        }
    }
}
