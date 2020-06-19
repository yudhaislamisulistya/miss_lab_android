package com.leadevs.misslab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.leadevs.misslab.R;
import com.leadevs.misslab.models.Absen;

import java.util.List;


public class AbsenAdapter extends RecyclerView.Adapter<AbsenAdapter.MyViewHolder> {

    private Context context ;
    private List<Absen> mData;
    private OnAbsenListener onAbsenListener;


    public AbsenAdapter(Context context, List<Absen> mData, OnAbsenListener onAbsenListener) {
        this.context = context;
        this.mData = mData;
        this.onAbsenListener = onAbsenListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_absen,viewGroup,false);
        return new MyViewHolder(view, onAbsenListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.TVStatusAbsen.setText(mData.get(i).getStatus());
        myViewHolder.TVNamaPraktikum.setText(mData.get(i).getNamaPraktikum());
        myViewHolder.TVMulaiAkhirPraktikum.setText(mData.get(i).getMulaiAkhirPraktikum());
        myViewHolder.TVRuanganPraktikum.setText(mData.get(i).getRuanganPraktikum());
        myViewHolder.TVNamaSekarang.setText(mData.get(i).getNamaSekarang());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView TVStatusAbsen;
        private TextView TVNamaPraktikum;
        private TextView TVMulaiAkhirPraktikum;
        private TextView TVRuanganPraktikum;
        private TextView TVNamaSekarang;
        private OnAbsenListener onAbsenListener;


        public MyViewHolder(@NonNull View itemView, OnAbsenListener onAbsenListener ) {

            super(itemView);
            TVStatusAbsen = itemView.findViewById(R.id.TVStatusAbsen);
            TVNamaPraktikum = itemView.findViewById(R.id.TVNamaPraktikum);
            TVMulaiAkhirPraktikum = itemView.findViewById(R.id.TVMulaiAkhirPraktikum);
            TVRuanganPraktikum = itemView.findViewById(R.id.TVRuanganPraktikum);
            TVNamaSekarang = itemView.findViewById(R.id.TVNamaSekarang);
            this.onAbsenListener = onAbsenListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAbsenListener.onAbsenListener(getAdapterPosition());
        }
    }

    public  interface OnAbsenListener{
        void onAbsenListener(int positition);
    }
}
