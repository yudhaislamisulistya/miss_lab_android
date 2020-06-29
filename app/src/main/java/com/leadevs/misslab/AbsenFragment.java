package com.leadevs.misslab;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.leadevs.misslab.adapters.AbsenAdapter;
import com.leadevs.misslab.models.Praktikum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AbsenFragment extends Fragment implements AbsenAdapter.OnAbsenListener {
    RecyclerView RVAbsen;
    AlertDialog alertDialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("practicum_schedules");
    CollectionReference collectionReferencePresent = db.collection("presents");
    ProgressDialog progressDialog;
    AbsenAdapter.OnAbsenListener onAbsenListener = this;
    List<Praktikum> daftarPraktikum = new ArrayList<>();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    AbsenAdapter absenAdapter;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_absen, container, false);
        RVAbsen = root.findViewById(R.id.RVItemAbsenHariIni);
        progressDialog = new ProgressDialog(getContext());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm"); //Date and time
        String currentTime = sdf.format(calendar.getTime());
        System.out.println(currentTime);

        SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String dayName = sdf_.format(date);
        String start_time = "", end_time = "";
        int currentTimeInteger = Integer.parseInt(currentTime);
        if (currentTimeInteger >= 800 && currentTimeInteger <= 940){
            start_time = "08:00";
            end_time = "09:40";
        }else if(currentTimeInteger >= 940 && currentTimeInteger <= 1120){
            start_time = "09:40";
            end_time = "11:20";
        }else if(currentTimeInteger >= 1300 && currentTimeInteger <= 1440){
            start_time = "13:00";
            end_time = "14:40";
        }else if(currentTimeInteger >= 1440 && currentTimeInteger <= 1620){
            start_time = "14:40";
            end_time = "16:20";
        }else if(currentTimeInteger >= 1620 && currentTimeInteger <= 1800){
            start_time = "";
            end_time = "";
        }
        setUpRecycleView(dayName, user.getUid(), start_time, end_time);
        return root;
    }


    public void setUpRecycleView(String hari, String id, String start_time, String end_time) {
        daftarPraktikum.clear();
        progressDialog.setTitle("Loading Data");
        progressDialog.show();
        collectionReference
                .whereEqualTo("day", hari)
                .whereEqualTo("assistant_one", id)
                .whereEqualTo("start_time", start_time)
                .whereEqualTo("end_time", end_time)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
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
                            absenAdapter = new AbsenAdapter(getContext(), daftarPraktikum, onAbsenListener);
                            RVAbsen.setAdapter(absenAdapter);
                            RVAbsen.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });

        collectionReference
                .whereEqualTo("day", hari)
                .whereEqualTo("assistant_two", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
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
                            absenAdapter = new AbsenAdapter(getContext(), daftarPraktikum, onAbsenListener);
                            RVAbsen.setAdapter(absenAdapter);
                            RVAbsen.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    public void addDataPresent(String id_user, String id_practicum_schedules, String status){
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        Map<String, Object>  presentData = new HashMap<>();
        presentData.put("id_user", id_user);
        presentData.put("id_practicum_schedules", id_practicum_schedules);
        presentData.put("status", status);
        presentData.put("created_at", timestamp);
        presentData.put("updated_at", timestamp);

        db.collection("presents")
                .add(presentData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Data Gagal Ditambahkan", Toast.LENGTH_LONG).show();
                    }
                });
    }




    @Override
    public void onAbsenListener(final int positition) {
        alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Absensi")
                .setMessage("Apakah Mau Mengabsensi Praktikum" + daftarPraktikum.get(positition).getName() + " " + daftarPraktikum.get(positition).getCode())
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addDataPresent(user.getUid(), daftarPraktikum.get(positition).getId(), "Berhasil");
                        absenAdapter.removeItem(positition);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                })
                .show();
    }
}