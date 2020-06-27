package com.leadevs.misslab.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.leadevs.misslab.R;
import com.leadevs.misslab.models.Informasi;


public class InformasiAdapter extends FirestoreRecyclerAdapter<Informasi, InformasiAdapter.MyViewHolder>  {

    OnInformasiListener onInformasiListener;

    public InformasiAdapter(@NonNull FirestoreRecyclerOptions<Informasi> options, OnInformasiListener onInformasiListener) {
        super(options);
        this.onInformasiListener = onInformasiListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Informasi model) {
        holder.TVJudul.setText(model.getTitle());
        holder.TVKonten.setText(model.getContent());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_informasi,viewGroup,false);
        return new MyViewHolder(view, onInformasiListener);
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
            int position = getAdapterPosition();
            onInformasiListener.onInformasiClick(getSnapshots().getSnapshot(getAdapterPosition()), getAdapterPosition());
        }
    }


    public  interface OnInformasiListener{
        void onInformasiClick(DocumentSnapshot documentSnapshot, int positition);
    }
}
