package com.leadevs.misslab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.leadevs.misslab.adapters.PraktikumAdapter;
import com.leadevs.misslab.models.Praktikum;

import java.util.ArrayList;
import java.util.List;

public class PraktikumGenapFragment extends Fragment implements PraktikumAdapter.OnPraktikumListener, AdapterView.OnItemSelectedListener {

    RecyclerView RVPraktikum;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("practicum_schedules");
    ProgressDialog progressDialog;
    PraktikumAdapter praktikumAdapter;
    PraktikumAdapter.OnPraktikumListener onPraktikumListener = this;
    Spinner spinner;
    List<Praktikum> daftarPraktikum = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_praktikum_genap, container, false);
        RVPraktikum = root.findViewById(R.id.RVItemPraktikumFP);
        progressDialog = new ProgressDialog(getContext());
        spinner = root.findViewById(R.id.SHari);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_nama_hari, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);
        setUpRecycleView("Senin", onPraktikumListener);

        return root;
    }

    public void setUpRecycleView(String hari, final PraktikumAdapter.OnPraktikumListener onPraktikumListener){
        progressDialog.setTitle("Loading Data");
        progressDialog.show();
        collectionReference
                .whereEqualTo("day", hari)
                .whereEqualTo("semester", "Genap")
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
                            praktikumAdapter = new PraktikumAdapter(getContext(),daftarPraktikum, onPraktikumListener);
                            RVPraktikum.setAdapter(praktikumAdapter);
                            RVPraktikum.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                        } else {
                            progressDialog.dismiss();
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    @Override
    public void onPraktikumListener(int positition) {
        Intent intent = new Intent(getContext(), DetailPraktikum.class);
        intent.putExtra("id", daftarPraktikum.get(positition).getId());
        intent.putExtra("name", daftarPraktikum.get(positition).getName());
        intent.putExtra("code", daftarPraktikum.get(positition).getCode());
        intent.putExtra("class_room", daftarPraktikum.get(positition).getClass_room());
        intent.putExtra("semester", daftarPraktikum.get(positition).getSemester());
        intent.putExtra("school_year", daftarPraktikum.get(positition).getSchool_year());
        intent.putExtra("assistant_one", daftarPraktikum.get(positition).getAssistant_one());
        intent.putExtra("assistant_two", daftarPraktikum.get(positition).getAssistant_two());
        intent.putExtra("lecture", daftarPraktikum.get(positition).getLecture());
        intent.putExtra("department", daftarPraktikum.get(positition).getDepartment());
        intent.putExtra("day", daftarPraktikum.get(positition).getDay());
        intent.putExtra("start_time", daftarPraktikum.get(positition).getStart_time());
        intent.putExtra("end_time", daftarPraktikum.get(positition).getEnd_time());
        intent.putExtra("name_image", daftarPraktikum.get(positition).getName_image());
        intent.putExtra("url_image", daftarPraktikum.get(positition).getUrl_image());
        intent.putExtra("created_at", daftarPraktikum.get(positition).getCreated_at());
        intent.putExtra("updated_at", daftarPraktikum.get(positition).getUpdated_at());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setUpRecycleView(parent.getSelectedItem().toString(), onPraktikumListener);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}