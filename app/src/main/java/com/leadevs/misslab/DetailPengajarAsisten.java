package com.leadevs.misslab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.leadevs.misslab.adapters.PraktikumAdapter;
import com.leadevs.misslab.adapters.PraktikumAdapter.OnPraktikumListener;
import com.leadevs.misslab.models.Praktikum;

import java.util.ArrayList;
import java.util.List;

public class DetailPengajarAsisten extends AppCompatActivity implements PraktikumAdapter.OnPraktikumListener {
    private RecyclerView RVDetailPengajarAsisten1, RVDetailPengajarAsisten2;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("practicum_schedules");
    ProgressDialog progressDialog;
    PraktikumAdapter praktikumAdapter;
    OnPraktikumListener onPraktikumListener = this;
    List<Praktikum> daftarPraktikum = new ArrayList<>();
    private TextView fullname;
    private TextView phone;
    private TextView gender;
    private TextView status_active;
    private TextView jumlah_praktikum;
    private TextView mengajar1;
    private TextView mengajar2;
    int jumlah_mengajar1 = 0;
    int jumlah_mengajar2 = 0;
    int jj_m1;
    int jj_m2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengajar_asisten);
        fullname = findViewById(R.id.TVDetailPANamaLengkap);
        phone = findViewById(R.id.TVDetailPANoTelp);
        gender = findViewById(R.id.TVDetailPAJenisKelamin);
        status_active = findViewById(R.id.TVDetailPAStatusActive);
        jumlah_praktikum = findViewById(R.id.TVDetailPDJumlahPraktikum);
        mengajar1 = findViewById(R.id.TVDetailPAMengajar1);
        mengajar2 = findViewById(R.id.TVDetailPAMengajar2);

        fullname.setText(getIntent().getStringExtra("fullname"));
        phone.setText(getIntent().getStringExtra("phone"));
        gender.setText(getIntent().getStringExtra("gender"));
        status_active.setText(getIntent().getStringExtra("status_active"));
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Data");
        progressDialog.show();
        RVDetailPengajarAsisten1 = findViewById(R.id.RVItemDetailPengajarAsisten1);
        RVDetailPengajarAsisten2 = findViewById(R.id.RVItemDetailPengajarAsisten2);




        collectionReference
                .whereEqualTo("assistant_one", getIntent().getStringExtra("stambuk"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            daftarPraktikum = new ArrayList<>();
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                jumlah_mengajar1 = jumlah_mengajar1 + 1;
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
                            if (jumlah_mengajar1 == 0){
                                mengajar1.setText("0 Praktikum");
                                jj_m1 = 0;
                            }else{
                                mengajar1.setText(String.valueOf(jumlah_mengajar1) + " Praktikum");
                                jj_m1 = daftarPraktikum.size();
                            }

                            praktikumAdapter = new PraktikumAdapter(getBaseContext(),daftarPraktikum, onPraktikumListener);
                            RVDetailPengajarAsisten1.setAdapter(praktikumAdapter);
                            RVDetailPengajarAsisten1.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });

        collectionReference
                .whereEqualTo("assistant_two", getIntent().getStringExtra("stambuk"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            daftarPraktikum = new ArrayList<>();
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                jumlah_mengajar2 = jumlah_mengajar2 + 1;
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
                            if (jumlah_mengajar2 == 0){
                                mengajar2.setText("0 Praktikum");
                                jj_m2 = 0;
                            }else{
                                mengajar2.setText(String.valueOf(jumlah_mengajar2) + " Praktikum");
                                jj_m2 = daftarPraktikum.size();
                            }
                            praktikumAdapter = new PraktikumAdapter(getBaseContext(),daftarPraktikum, onPraktikumListener);
                            RVDetailPengajarAsisten2.setAdapter(praktikumAdapter);
                            RVDetailPengajarAsisten2.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
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