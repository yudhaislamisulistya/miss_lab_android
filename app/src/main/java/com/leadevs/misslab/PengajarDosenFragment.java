package com.leadevs.misslab;

import android.app.ProgressDialog;
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
import com.leadevs.misslab.adapters.DosenGridViewAdapter;
import com.leadevs.misslab.models.Dosen;

import java.util.ArrayList;
import java.util.List;

public class PengajarDosenFragment extends Fragment {
    GridView gridView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_pengajar_dosen, container, false);
        gridView = root.findViewById(R.id.GVDosen);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading Data");
        progressDialog.show();
        db.collection("lectures")
                .orderBy("created_at", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        final List<Dosen> daftarDosen = new ArrayList<>();
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
                            DosenGridViewAdapter dosenAdapter = new DosenGridViewAdapter(getContext(), daftarDosen);
                            gridView.setAdapter(dosenAdapter);
                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getContext(), DetailPengajarDosen.class);
                                    intent.putExtra("id", daftarDosen.get(position).getId());
                                    intent.putExtra("id_user", daftarDosen.get(position).getId_user());
                                    intent.putExtra("fullname", daftarDosen.get(position).getFullname());
                                    intent.putExtra("nidn", daftarDosen.get(position).getNidn());
                                    intent.putExtra("gender", daftarDosen.get(position).getGender());
                                    intent.putExtra("phone", daftarDosen.get(position).getPhone());
                                    intent.putExtra("url_image", daftarDosen.get(position).getUrl_image());
                                    startActivity(intent);
                                }
                            });
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });

        return root;
    }
}