package com.leadevs.misslab;

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
    ArrayList<Informasi> informasis = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("informations");
    InformasiAdapter.OnInformasiListener onInformasiListener;
    InformasiAdapter informasiAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_informasi, container, false);
        RVInformasi = root.findViewById(R.id.RVItemInformasi);
        onInformasiListener = this;
//        db.collection("informations")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        List<Informasi> daftarInformasi = new ArrayList<>();
//                        if (task.isSuccessful()) {
//                            informasis.clear();
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                daftarInformasi.add(new Informasi(document.getData().get("id").toString(), document.getData().get("title").toString(), document.getData().get("content").toString(), document.getData().get("for").toString(), document.getData().get("created_at").toString(), document.getData().get("updated_at").toString()));
//                            }
//                            informasiAdapter = new InformasiAdapter(getContext(),daftarInformasi, onInformasiListener);
//                            RVInformasi.setAdapter(informasiAdapter);
//                            RVInformasi.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
//                        } else {
//                            System.out.println(task.getException());
//                        }
//                    }
//                });
        setUpRecycleView();
        return root;
    }

    public void setUpRecycleView() {
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