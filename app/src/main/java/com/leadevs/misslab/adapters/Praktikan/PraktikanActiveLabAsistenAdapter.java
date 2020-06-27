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
import com.leadevs.misslab.models.ActiveLab;


public class PraktikanActiveLabAsistenAdapter extends FirestoreRecyclerAdapter<ActiveLab, PraktikanActiveLabAsistenAdapter.MyViewHolder>  {

    OnPraktikanActiveLabAsistneListener onPraktikanActiveLabAsistenListener;

    public PraktikanActiveLabAsistenAdapter(@NonNull FirestoreRecyclerOptions<ActiveLab> options, OnPraktikanActiveLabAsistneListener onPraktikanActiveLabAsistenListener) {
        super(options);
        this.onPraktikanActiveLabAsistenListener = onPraktikanActiveLabAsistenListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull ActiveLab model) {
        holder.TVNamaLengkap.setText(model.getFullname());
        holder.TVStatus.setText(model.getStatus_active());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_praktikan_active_lab_asisten,viewGroup,false);
        return new MyViewHolder(view, onPraktikanActiveLabAsistenListener);
    }




    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView TVNamaLengkap;
        private TextView TVStatus;
        private OnPraktikanActiveLabAsistneListener onPraktikanActiveLabAsistenListener;

        public MyViewHolder(@NonNull View itemView, OnPraktikanActiveLabAsistneListener onPraktikanActiveLabAsistenListener) {

            super(itemView);
            TVNamaLengkap = itemView.findViewById(R.id.item_active_lab_asisten_nama_lengkap);
            TVStatus = itemView.findViewById(R.id.item_active_lab_asisten_status);
            this.onPraktikanActiveLabAsistenListener = onPraktikanActiveLabAsistenListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onPraktikanActiveLabAsistenListener.onPraktikanActiveLabAsistenClick(getSnapshots().getSnapshot(getAdapterPosition()), getAdapterPosition());
        }
    }


    public  interface OnPraktikanActiveLabAsistneListener{
        void onPraktikanActiveLabAsistenClick(DocumentSnapshot documentSnapshot, int positition);
    }
}
