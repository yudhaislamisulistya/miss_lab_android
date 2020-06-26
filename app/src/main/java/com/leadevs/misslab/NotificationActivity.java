package com.leadevs.misslab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.leadevs.misslab.adapters.NotificationAdapter;
import com.leadevs.misslab.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity implements NotificationAdapter.OnNotificationListener {

    private RecyclerView RVItemNotification;
    ArrayList<Notification> notificationActivities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        RVItemNotification = findViewById(R.id.RVItemNoitication);

        List<Notification> daftarNotifikasi = getDataNotification();

        NotificationAdapter notificationAdapter = new NotificationAdapter(getBaseContext(),daftarNotifikasi, this);
        RVItemNotification.setAdapter(notificationAdapter);
        RVItemNotification.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
    }


    private ArrayList<Notification> getDataNotification(){
        notificationActivities.clear();
        notificationActivities.add(new Notification(1, "Pemrograman Java Lanjut C1", "Praktikun Akan Dimulai 10 Meni Lagi"));
        notificationActivities.add(new Notification(2, "Pemrograman Visual C2", "Praktikun Akan Dimulai 10 Meni Lagi"));
        notificationActivities.add(new Notification(3, "Struktur Data A1", "Praktikun Akan Dimulai 10 Meni Lagi"));
        notificationActivities.add(new Notification(4, "Pemrograman Java Lanjut C2", "Praktikun Akan Dimulai 10 Meni Lagi"));
        return notificationActivities;
    }

    @Override
    public void onNotificationClick(int positition) {

    }
}