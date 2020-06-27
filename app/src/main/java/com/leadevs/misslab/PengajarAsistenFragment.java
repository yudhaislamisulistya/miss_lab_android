package com.leadevs.misslab;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.leadevs.misslab.adapters.AsistenGridViewAdapter;
import com.leadevs.misslab.models.Asisten;

import java.util.ArrayList;
import java.util.List;

public class PengajarAsistenFragment extends Fragment {

    GridView gridView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_pengajar_asisten, container, false);
        gridView = root.findViewById(R.id.GVAsisten);

        db.collection("assistants")
                .orderBy("created_at", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        final List<Asisten> daftarAsisten = new ArrayList<>();
                        if (task.isSuccessful()) {
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
                            AsistenGridViewAdapter asistenGridViewAdapter = new AsistenGridViewAdapter(getContext(), daftarAsisten);
                            gridView.setAdapter(asistenGridViewAdapter);
                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getContext(), DetailPengajarAsisten.class);
                                    intent.putExtra("id", daftarAsisten.get(position).getId());
                                    intent.putExtra("id_user", daftarAsisten.get(position).getId_user());
                                    intent.putExtra("fullname", daftarAsisten.get(position).getFullname());
                                    intent.putExtra("stambuk", daftarAsisten.get(position).getStambuk());
                                    intent.putExtra("status_active", daftarAsisten.get(position).getStatus_active());
                                    intent.putExtra("gender", daftarAsisten.get(position).getGender());
                                    intent.putExtra("phone", daftarAsisten.get(position).getPhone());
                                    intent.putExtra("address", daftarAsisten.get(position).getAddress());
                                    intent.putExtra("url_image", daftarAsisten.get(position).getUrl_image());
                                    startActivity(intent);
                                }
                            });
                        } else {
                            System.out.println(task.getException());
                        }
                    }
                });
        return root;
    }
}