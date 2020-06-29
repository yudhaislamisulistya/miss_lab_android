package com.leadevs.misslab;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.leadevs.misslab.adapters.AbsenAdapter;
import com.leadevs.misslab.adapters.PraktikumAdapter;
import com.leadevs.misslab.models.Absen;
import com.leadevs.misslab.models.Praktikum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AbsenFragment extends Fragment implements AbsenAdapter.OnAbsenListener {
    RecyclerView RVAbsen;
    AlertDialog alertDialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("practicum_schedules");
    CollectionReference collectionReferenceAsisten = db.collection("assistants");
    ProgressDialog progressDialog;
    AbsenAdapter.OnAbsenListener onAbsenListener = this;
    List<Praktikum> daftarPraktikum = new ArrayList<>();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String stambuk;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_absen, container, false);
        RVAbsen = root.findViewById(R.id.RVItemAbsenHariIni);
        progressDialog = new ProgressDialog(getContext());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm a"); //Date and time
        String currentDate = sdf.format(calendar.getTime());

        SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String dayName = sdf_.format(date);
        setUpRecycleView(dayName, getStambukAsisten(user.getUid()));
        return root;
    }

    public String getStambukAsisten(String id){
        progressDialog.setTitle("Loading Data");
        progressDialog.show();
        collectionReferenceAsisten
                .document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    System.out.println(document);
                    if (document != null) {
                      stambuk = document.getString("stambuk");
                    } else {
                        progressDialog.dismiss();
                    }
                } else {
                    progressDialog.dismiss();
                }
            }
        });
        return stambuk;
    }


    public void setUpRecycleView(String hari, String id){
        progressDialog.setTitle("Loading Data");
        progressDialog.show();
        collectionReference
                .whereEqualTo("day", hari)
                .whereEqualTo("assistant_one", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        daftarPraktikum = new ArrayList<>();
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
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
                            AbsenAdapter absenAdapter = new AbsenAdapter(getContext(),daftarPraktikum, onAbsenListener);
                            RVAbsen.setAdapter(absenAdapter);
                            RVAbsen.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    private ArrayList<Absen> getDataAbsen(){
        ArrayList<Absen> absens = new ArrayList<>();
        absens.add(new Absen(1, "Yudha Islami Sulistya", "Java Lanjut", "09.00 - 10.00", "Lab RPL", "Belum Absen"));
        absens.add(new Absen(1, "Yudha Islami Sulistya", "Java Lanjut", "09.00 - 10.00", "Lab RPL", "Belum Absen"));
        absens.add(new Absen(1, "Yudha Islami Sulistya", "Java Lanjut", "09.00 - 10.00", "Lab RPL", "Belum Absen"));
        return absens;
    }

    @Override
    public void onAbsenListener(int positition) {
        alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Kamu Mau Mengabsen")
                .setPositiveButton("Ok", null)
                .setNegativeButton("Batal", null)
                .show();
        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}