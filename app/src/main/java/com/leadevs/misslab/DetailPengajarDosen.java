package com.leadevs.misslab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.leadevs.misslab.adapters.DetailPengajarAsistenAdapter;
import com.leadevs.misslab.adapters.PraktikumAdapter;
import com.leadevs.misslab.models.DetailPengajar;
import com.leadevs.misslab.models.Praktikum;
import com.leadevs.misslab.adapters.PraktikumAdapter.OnPraktikumListener;


import java.util.ArrayList;
import java.util.List;

public class DetailPengajarDosen extends AppCompatActivity implements PraktikumAdapter.OnPraktikumListener {
    private RecyclerView RVDetailPengajar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("practicum_schedules");
    ProgressDialog progressDialog;
    PraktikumAdapter praktikumAdapter;
    OnPraktikumListener onPraktikumListener = this;
    List<Praktikum> daftarPraktikum = new ArrayList<>();
    private TextView fullname;
    private TextView phone;
    private TextView gender;
    private TextView jumlah_praktikum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengajar_dosen);
        fullname = findViewById(R.id.TVDetailPDNamaLengkap);
        phone = findViewById(R.id.TVDetailPDNoTelp);
        gender = findViewById(R.id.TVDetailPDJenisKelamin);
        jumlah_praktikum = findViewById(R.id.TVDetailPDJumlahPraktikum);

        fullname.setText(getIntent().getStringExtra("fullname"));
        phone.setText(getIntent().getStringExtra("phone"));
        gender.setText(getIntent().getStringExtra("gender"));

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Data");
        progressDialog.show();


        RVDetailPengajar = findViewById(R.id.RVItemDetailPengajarDosen);

        collectionReference
                .whereEqualTo("lecture", getIntent().getStringExtra("id_user"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            daftarPraktikum = new ArrayList<>();
                            progressDialog.dismiss();
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                i = i + 1;
                                String id = document.getData().get("id").toString();
                                String name = document.getData().get("name").toString();
                                String code = document.getData().get("code").toString();
                                String class_room = document.getData().get("class_room").toString();
                                String semester = document.getData().get("semester").toString();
                                String school_year = document.getData().get("school_year").toString();
                                String assistant_one = document.getData().get("assistant_one").toString();
                                String assistant_two = document.getData().get("assistant_two").toString();
                                String lecture = document.getData().get("lecture").toString();
                                String department = document.getData().get("department").toString();
                                String day = document.getData().get("day").toString();
                                String start_time = document.getData().get("start_time").toString();
                                String end_time = document.getData().get("end_time").toString();
                                String name_image = document.getData().get("name_image").toString();
                                String url_image = document.getData().get("url_image").toString();
                                Timestamp created_at = document.getTimestamp("created_at");
                                Timestamp updated_at = document.getTimestamp("updated_at");
                                daftarPraktikum.add(new Praktikum(id, name, code, class_room, semester, school_year, assistant_one, assistant_two, lecture, department, day, start_time, end_time, name_image, url_image, created_at, updated_at));
                            }
                            jumlah_praktikum.setText(String.valueOf(i));
                            praktikumAdapter = new PraktikumAdapter(getBaseContext(),daftarPraktikum, onPraktikumListener);
                            RVDetailPengajar.setAdapter(praktikumAdapter);
                            RVDetailPengajar.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });
    }




    @Override
    public void onPraktikumListener(int positition) {

    }
}