package com.leadevs.misslab.adapters.Praktikan;

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


public class PraktikanInformasiAdapter extends FirestoreRecyclerAdapter<Informasi, PraktikanInformasiAdapter.MyViewHolder>  {

    OnPraktikanInformasiListener onPraktikanInformasiListener;

    public PraktikanInformasiAdapter(@NonNull FirestoreRecyclerOptions<Informasi> options, OnPraktikanInformasiListener onPraktikanInformasiListener) {
        super(options);
        this.onPraktikanInformasiListener = onPraktikanInformasiListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Informasi model) {
        holder.TVJudul.setText(model.getTitle());
        holder.TVKonten.setText(model.getContent());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.praktikan_item_informasi,viewGroup,false);
        return new MyViewHolder(view, onPraktikanInformasiListener);
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
            int position = getAdapterPosition();
            onPraktikanInformasiListener.onPraktikanInformasiClick(getSnapshots().getSnapshot(getAdapterPosition()), getAdapterPosition());
        }
    }


    public  interface OnPraktikanInformasiListener{
        void onPraktikanInformasiClick(DocumentSnapshot documentSnapshot, int positition);
    }
}
