package com.leadevs.misslab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.leadevs.misslab.adapters.AsistenGridViewAdapter;
import com.leadevs.misslab.adapters.PraktikanGridViewAdapter;
import com.leadevs.misslab.adapters.PraktikumAdapter;
import com.leadevs.misslab.models.Asisten;
import com.leadevs.misslab.models.Praktikan;
import com.leadevs.misslab.models.Praktikum;

import java.util.ArrayList;
import java.util.List;

public class DetailPraktikum extends AppCompatActivity {

    GridView gridView;
    private TextView TVDetailPraktikumNama;
    private TextView TVDetailPraktikumWaktu;
    private TextView TVDetailPraktikumJurusanDanSemester;
    private TextView TVDetailTahunAjaran;
    private TextView TVDetailPraktikumRuangan;
    private TextView TVDetailPraktikumNamaDosen;
    private TextView TVDetailPraktikumNamaAsisten1;
    private TextView TVDetailPraktikumNamaAsisten2;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferenceAsisten = db.collection("assistants");
    private CollectionReference collectionReferenceDosen = db.collection("lectures");
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_praktikum);
        gridView = findViewById(R.id.GVPraktikan);
        TVDetailPraktikumNama = findViewById(R.id.TVDetailPraktikumNama);
        TVDetailPraktikumWaktu = findViewById(R.id.TVDetailPraktikumWaktu);
        TVDetailPraktikumJurusanDanSemester = findViewById(R.id.TVDetailPraktikumJurusanDanSemester);
        TVDetailTahunAjaran = findViewById(R.id.TVDetailTahunAjaran);
        TVDetailPraktikumRuangan = findViewById(R.id.TVDetailPraktikumRuangan);
        TVDetailPraktikumNamaDosen = findViewById(R.id.TVDetailPraktikumNamaDosen);
        TVDetailPraktikumNamaAsisten1 = findViewById(R.id.TVDetailPraktikumNamaAsisten1);
        TVDetailPraktikumNamaAsisten2 = findViewById(R.id.TVDetailPraktikumNamaAsisten2);

        TVDetailPraktikumNama.setText(getIntent().getStringExtra("name") + " " + getIntent().getStringExtra("code"));
        TVDetailPraktikumWaktu.setText(getIntent().getStringExtra("day") + ", " + getIntent().getStringExtra("start_time") + " - " + getIntent().getStringExtra("end_time"));
        TVDetailPraktikumJurusanDanSemester.setText(getIntent().getStringExtra("department") + " - " + getIntent().getStringExtra("semester"));
        TVDetailTahunAjaran.setText(getIntent().getStringExtra("school_year"));
        TVDetailPraktikumRuangan.setText(getIntent().getStringExtra("class_room"));
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Data");
        progressDialog.show();

        collectionReferenceAsisten
                .whereEqualTo("id_user", getIntent().getStringExtra("assistant_one"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TVDetailPraktikumNamaAsisten1.setText(document.getData().get("fullname").toString());
                            }
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });

        collectionReferenceAsisten
                .whereEqualTo("id_user", getIntent().getStringExtra("assistant_two"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TVDetailPraktikumNamaAsisten2.setText(document.getData().get("fullname").toString());
                            }
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });

        collectionReferenceDosen
                .whereEqualTo("nidn", getIntent().getStringExtra("lecture"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TVDetailPraktikumNamaDosen.setText(document.getData().get("fullname").toString());
                            }
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });



        final List<Praktikan> daftarAsisten = getDataAsisten();
        PraktikanGridViewAdapter praktikanGridViewAdapter = new PraktikanGridViewAdapter(getApplicationContext(), daftarAsisten);
        gridView.setAdapter(praktikanGridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), DetailPengajarAsisten.class));
            }
        });
    }

    private ArrayList<Praktikan> getDataAsisten(){
        ArrayList<Praktikan> praktikans = new ArrayList<>();
        praktikans.add(new Praktikan(1, "13020170214", "Andi Basso", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Lala", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Gon", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Aku", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Biji", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Haiii", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Cuaks", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        return praktikans;
    }
}