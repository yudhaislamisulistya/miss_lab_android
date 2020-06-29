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
import com.leadevs.misslab.models.Praktikum;

import java.util.List;


public class AbsenAdapter extends RecyclerView.Adapter<AbsenAdapter.MyViewHolder> {

    private Context context ;
    private List<Praktikum> mData;
    private OnAbsenListener onAbsenListener;


    public AbsenAdapter(Context context, List<Praktikum> mData, OnAbsenListener onAbsenListener) {
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
        myViewHolder.TVNamaPraktikum.setText(mData.get(i).getName());
        myViewHolder.TVMulaiAkhirPraktikum.setText(mData.get(i).getDay() + ", " + mData.get(i).getStart_time() + " - " + mData.get(i).getEnd_time());
        myViewHolder.TVRuanganPraktikum.setText(mData.get(i).getClass_room());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView TVNamaPraktikum;
        private TextView TVMulaiAkhirPraktikum;
        private TextView TVRuanganPraktikum;
        private OnAbsenListener onAbsenListener;


        public MyViewHolder(@NonNull View itemView, OnAbsenListener onAbsenListener ) {

            super(itemView);
            TVNamaPraktikum = itemView.findViewById(R.id.TVNamaPraktikum);
            TVMulaiAkhirPraktikum = itemView.findViewById(R.id.TVMulaiAkhirPraktikum);
            TVRuanganPraktikum = itemView.findViewById(R.id.TVRuanganPraktikum);
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
