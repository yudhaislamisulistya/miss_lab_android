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

    private Context context ;
    private List<Informasi> mData;
    private OnInformasiListener onInformasiListener;


    public InformasiAdapter(Context context, List<Informasi> mData, OnInformasiListener onInformasiListener) {
        this.context = context;
        this.mData = mData;
        this.onInformasiListener = onInformasiListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_informasi,viewGroup,false);
        return new MyViewHolder(view, onInformasiListener);
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
        private OnInformasiListener onInformasiListener;

        public MyViewHolder(@NonNull View itemView, OnInformasiListener onInformasiListener) {

            super(itemView);
            TVJudul = itemView.findViewById(R.id.item_informasi_judul);
            TVKonten = itemView.findViewById(R.id.item_informasi_konten);
            this.onInformasiListener = onInformasiListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onInformasiListener.onInformasiClick(getAdapterPosition());

        }
    }

    public  interface OnInformasiListener{
        void onInformasiClick(int positition);
    }
}
