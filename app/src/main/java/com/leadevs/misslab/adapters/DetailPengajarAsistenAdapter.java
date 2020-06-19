package com.leadevs.misslab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.leadevs.misslab.R;
import com.leadevs.misslab.models.DetailPengajar;

import java.util.List;


public class DetailPengajarAsistenAdapter extends RecyclerView.Adapter<DetailPengajarAsistenAdapter.MyViewHolder> {

    private Context context ;
    private List<DetailPengajar> mData;
    private OnDetailPengajarListener onDetailPengajarListener;


    public DetailPengajarAsistenAdapter(Context context, List<DetailPengajar> mData, OnDetailPengajarListener onDetailPengajarListener) {
        this.context = context;
        this.mData = mData;
        this.onDetailPengajarListener = onDetailPengajarListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_pengajar_asisten_detail_absensi,viewGroup,false);
        return new MyViewHolder(view, onDetailPengajarListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.TVStatusAbsen.setText(mData.get(i).getStatusAbsen());
        myViewHolder.TVNamaPraktikum.setText(mData.get(i).getNamaPraktikum());
        myViewHolder.TVHariWaktuPraktikum.setText(mData.get(i).getHariWaktuPraktikum());
        myViewHolder.TVRuanganPraktikum.setText(mData.get(i).getRuanganPraktikum());
        myViewHolder.TVStatusAsisten.setText(mData.get(i).getStatus());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView TVStatusAbsen;
        private TextView TVNamaPraktikum;
        private TextView TVHariWaktuPraktikum;
        private TextView TVRuanganPraktikum;
        private TextView TVStatusAsisten;
        private OnDetailPengajarListener onDetailPengajarListener;

        public MyViewHolder(@NonNull View itemView, OnDetailPengajarListener onDetailPengajarListener) {

            super(itemView);
            TVStatusAbsen = itemView.findViewById(R.id.TVStatusAbsen);
            TVNamaPraktikum = itemView.findViewById(R.id.TVNamaPraktikum);
            TVHariWaktuPraktikum = itemView.findViewById(R.id.TVHariWaktuPraktikum);
            TVRuanganPraktikum = itemView.findViewById(R.id.TVRuanganPraktikum);
            TVStatusAsisten = itemView.findViewById(R.id.TVStatusAsisten);
            this.onDetailPengajarListener = onDetailPengajarListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onDetailPengajarListener.onDetailPengajarListener(getAdapterPosition());

        }
    }

    public  interface OnDetailPengajarListener{
        void onDetailPengajarListener(int positition);
    }
}
