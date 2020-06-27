package com.leadevs.misslab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.Transliterator;
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

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.leadevs.misslab.adapters.AsistenAdapter;
import com.leadevs.misslab.adapters.InformasiAdapter;
import com.leadevs.misslab.models.Asisten;
import com.leadevs.misslab.models.Informasi;
import com.leadevs.misslab.models.Praktikum;

import java.util.ArrayList;
import java.util.List;

public class InformasiFragment extends Fragment implements InformasiAdapter.OnInformasiListener {

    private RecyclerView RVInformasi;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("informations");
    InformasiAdapter informasiAdapter;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_informasi, container, false);
        RVInformasi = root.findViewById(R.id.RVItemInformasi);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading Data Informasi");
        progressDialog.show();
        setUpRecycleView();
        return root;
    }

    public void setUpRecycleView() {
        progressDialog.dismiss();
        Query query = collectionReference.orderBy("created_at", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Informasi> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Informasi>()
                .setQuery(query, Informasi.class)
                .build();
        informasiAdapter = new InformasiAdapter(firestoreRecyclerOptions, this);
        RVInformasi.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        RVInformasi.setAdapter(informasiAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        informasiAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        informasiAdapter.stopListening();
    }


    @Override
    public void onInformasiClick(DocumentSnapshot documentSnapshot, int positition) {
        Informasi informasi = documentSnapshot.toObject(Informasi.class);
        Intent intent = new Intent(getContext(), DetailInformasi.class);
        intent.putExtra("title", informasi.getTitle());
        intent.putExtra("content", informasi.getContent());
        startActivity(intent);
    }
}