package com.leadevs.misslab.adapters.Praktikan;

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


public class PraktikanInformasiAdapter extends RecyclerView.Adapter<PraktikanInformasiAdapter.MyViewHolder> {

    private Context context ;
    private List<Informasi> mData;
    private OnPraktikanInformasiListener onPraktikanInformasiListener;


    public PraktikanInformasiAdapter(Context context, List<Informasi> mData, OnPraktikanInformasiListener onPraktikanInformasiListener) {
        this.context = context;
        this.mData = mData;
        this.onPraktikanInformasiListener = onPraktikanInformasiListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.praktikan_item_informasi,viewGroup,false);
        return new MyViewHolder(view, onPraktikanInformasiListener);
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView TVJudul;
        private TextView TVKonten;
        private OnPraktikanInformasiListener onPraktikanInformasiListener;

        public MyViewHolder(@NonNull View itemView, OnPraktikanInformasiListener onPraktikanInformasiListener) {

            super(itemView);
            TVJudul = itemView.findViewById(R.id.item_informasi_judul);
            TVKonten = itemView.findViewById(R.id.item_informasi_konten);
            this.onPraktikanInformasiListener = onPraktikanInformasiListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPraktikanInformasiListener.onPraktikanInformasiClick(getAdapterPosition());
        }
    }

    public  interface OnPraktikanInformasiListener{
        void onPraktikanInformasiClick(int positition);
    }
}
