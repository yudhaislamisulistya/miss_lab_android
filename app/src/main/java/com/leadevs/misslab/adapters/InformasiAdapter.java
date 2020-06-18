package com.leadevs.misslab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.leadevs.misslab.R;
import com.leadevs.misslab.models.Informasi;

import java.util.List;


public class InformasiAdapter extends RecyclerView.Adapter<InformasiAdapter.MyViewHolder> {

    Context context ;
    List<Informasi> mData;


    public InformasiAdapter(Context context, List<Informasi> mData) {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_informasi,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.TVJudul.setText(mData.get(i).getJudul());
        myViewHolder.TVKonten.setText(mData.get(i).getKonten());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView TVJudul;
        private TextView TVKonten;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            TVJudul = itemView.findViewById(R.id.item_informasi_judul);
            TVKonten = itemView.findViewById(R.id.item_informasi_konten);
        }
    }
}
