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
import com.leadevs.misslab.models.Praktikum;

import java.util.List;


public class PraktikumAdapter extends RecyclerView.Adapter<PraktikumAdapter.MyViewHolder> {

    private Context context ;
    private List<Praktikum> mData;
    private OnPraktikumListener onPraktikumListener;


    public PraktikumAdapter(Context context, List<Praktikum> mData, OnPraktikumListener onPraktikumListener) {
        this.context = context;
        this.mData = mData;
        this.onPraktikumListener = onPraktikumListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_praktikum,viewGroup,false);
        return new MyViewHolder(view, onPraktikumListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.TVNamaPraktikum.setText(mData.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView TVNamaPraktikum;
        private OnPraktikumListener onPraktikumListener;

        public MyViewHolder(@NonNull View itemView, OnPraktikumListener onPraktikumListener) {

            super(itemView);
            TVNamaPraktikum = itemView.findViewById(R.id.item_praktikum_title);
            this.onPraktikumListener = onPraktikumListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPraktikumListener.onPraktikumListener(getAdapterPosition());

        }
    }

    public  interface OnPraktikumListener{
        void onPraktikumListener(int positition);
    }
}
