package com.leadevs.misslab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.leadevs.misslab.R;
import com.leadevs.misslab.models.Notification;

import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private Context context ;
    private List<Notification> mData;
    private OnNotificationListener onNotificationListener;


    public NotificationAdapter(Context context, List<Notification> mData, OnNotificationListener onNotificationListener) {
        this.context = context;
        this.mData = mData;
        this.onNotificationListener = onNotificationListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification,viewGroup,false);
        return new MyViewHolder(view, onNotificationListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.TVNamaPraktikum.setText(mData.get(i).getNamaPraktikum());
        myViewHolder.TVNotif.setText(mData.get(i).getNotif());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView TVNamaPraktikum;
        private TextView TVNotif;
        private OnNotificationListener onNotificationListener;

        public MyViewHolder(@NonNull View itemView, OnNotificationListener onNotificationListener) {
            super(itemView);
            TVNamaPraktikum = itemView.findViewById(R.id.item_notification_nama_praktikum);
            TVNotif = itemView.findViewById(R.id.item_notification_notif);
            this.onNotificationListener = onNotificationListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNotificationListener.onNotificationClick(getAdapterPosition());
        }
    }

    public  interface OnNotificationListener{
        void onNotificationClick(int positition);
    }
}
