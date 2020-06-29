package com.leadevs.misslab;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.leadevs.misslab.adapters.AsistenAdapter;
import com.leadevs.misslab.adapters.DosenAdapter;
import com.leadevs.misslab.adapters.PraktikumHomeAdapter;
import com.leadevs.misslab.models.Asisten;
import com.leadevs.misslab.models.Dosen;
import com.leadevs.misslab.models.Praktikum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment {
    private RecyclerView RVPraktikum, RVDosen, RVAsisten;
    private TextView TVHomeFragmentStatus, TVHomeFragmentNamaLengkap;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReferencePraktikum = db.collection("practicum_schedules");
    CollectionReference collectionReferenceDosen = db.collection("lectures");
    CollectionReference collectionReferenceAsisten = db.collection("assistants");
    CollectionReference collectionReferenceUser = db.collection("users");
    CollectionReference collectionReferenceActiveLab = db.collection("actives_lab");
    ProgressDialog progressDialog;
    PraktikumHomeAdapter praktikumHomeAdapter;
    AsistenAdapter asistenAdapter;
    DosenAdapter dosenAdapter;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Switch SHomeFragmentStatusActive;
    String uuid;
    String status_active = "Tidak Ada";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RVPraktikum = root.findViewById(R.id.RVItemPraktikum);
        RVDosen = root.findViewById(R.id.RVItemDosen);
        RVAsisten = root.findViewById(R.id.RVItemAsisten);
        TVHomeFragmentNamaLengkap = root.findViewById(R.id.TVHomeFragmentNamaLengkap);
        TVHomeFragmentStatus = root.findViewById(R.id.TVHomeFragmentStatus);
        SHomeFragmentStatusActive = root.findViewById(R.id.SHomeFragmentStatusActive);
        progressDialog = new ProgressDialog(getContext());
        updateNamaLengkapDanStatus(user.getUid());
        setUpRecycleViewPraktikum();
        setUpRecycleViewAsisten();
        setUpRecycleViewDosen();



        String status = getStatusIdActiveLab(user.getUid());

        if (status.equals("Ada")){
            SHomeFragmentStatusActive.setChecked(true);
        }else{
            SHomeFragmentStatusActive.setChecked(false);
        }

        SHomeFragmentStatusActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SHomeFragmentStatusActive.isChecked()) {
                    updateStatusActiveLabAsisten(user.getUid(), "Ada");
                } else {
                    updateStatusActiveLabAsisten(user.getUid(), "Tidak Ada");
                }
            }
        });
        return root;
    }

    public void updateStatusActiveLabAsisten(String id, String status) {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("status_active", status);
        progressDialog.setTitle("Proses Ubah");
        progressDialog.show();
        collectionReferenceActiveLab
                .document(id)
                .set(statusMap, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Status Berhasil Diubah",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Status Gagal Diubah",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public String getStatusIdActiveLab(String id) {
        collectionReferenceActiveLab
                .whereEqualTo("id", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                status_active = document.getData().get("status_active").toString();
                            }
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });
        return status_active;
    }



    public void updateNamaLengkapDanStatus(String id) {
        collectionReferenceUser
                .whereEqualTo("id", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TVHomeFragmentNamaLengkap.setText(document.getData().get("fullname").toString());
                                TVHomeFragmentStatus.setText(document.getData().get("status").toString());
                            }
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    public void setUpRecycleViewDosen() {
        progressDialog.setTitle("Loading Data");
        progressDialog.show();
        collectionReferenceDosen
                .limit(5)
                .orderBy("created_at", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Dosen> daftarDosen = new ArrayList<>();
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getData().get("id").toString();
                                String id_user = document.getData().get("id_user").toString();
                                String fullname = document.getData().get("fullname").toString();
                                String nidn = document.getData().get("nidn").toString();
                                String gender = document.getData().get("gender").toString();
                                String phone = document.getData().get("phone").toString();
                                String name_image = document.getData().get("name_image").toString();
                                String url_image = document.getData().get("url_image").toString();
                                Timestamp created_at = document.getTimestamp("created_at");
                                Timestamp updated_at = document.getTimestamp("updated_at");
                                daftarDosen.add(new Dosen(id, id_user, fullname, nidn, gender, phone, name_image, url_image, created_at, updated_at));
                            }
                            dosenAdapter = new DosenAdapter(getContext(), daftarDosen);
                            RVDosen.setAdapter(dosenAdapter);
                            RVDosen.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    public void setUpRecycleViewAsisten() {
        progressDialog.setTitle("Loading Data");
        progressDialog.show();
        collectionReferenceAsisten
                .limit(5)
                .orderBy("created_at", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Asisten> daftarAsisten = new ArrayList<>();
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getData().get("id").toString();
                                String id_user = document.getData().get("id_user").toString();
                                String fullname = document.getData().get("fullname").toString();
                                String stambuk = document.getData().get("stambuk").toString();
                                String status_active = document.getData().get("status_active").toString();
                                String gender = document.getData().get("gender").toString();
                                String phone = document.getData().get("phone").toString();
                                String address = document.getData().get("address").toString();
                                String name_image = document.getData().get("name_image").toString();
                                String url_image = document.getData().get("url_image").toString();
                                Timestamp created_at = document.getTimestamp("created_at");
                                Timestamp updated_at = document.getTimestamp("updated_at");
                                daftarAsisten.add(new Asisten(id, id_user, fullname, stambuk, status_active, gender, phone, address, name_image, url_image, created_at, updated_at));
                            }
                            asistenAdapter = new AsistenAdapter(getContext(), daftarAsisten);
                            RVAsisten.setAdapter(asistenAdapter);
                            RVAsisten.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    public void setUpRecycleViewPraktikum() {
        progressDialog.setTitle("Loading Data");
        progressDialog.show();
        collectionReferencePraktikum
                .limit(5)
                .orderBy("created_at", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Praktikum> daftarPraktikum = new ArrayList<>();
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
                            praktikumHomeAdapter = new PraktikumHomeAdapter(getContext(), daftarPraktikum);
                            RVPraktikum.setAdapter(praktikumHomeAdapter);
                            RVPraktikum.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });
    }


}