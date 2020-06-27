package com.leadevs.misslab.ui.praktikan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.leadevs.misslab.DetailInformasi;
import com.leadevs.misslab.R;
import com.leadevs.misslab.adapters.InformasiAdapter;
import com.leadevs.misslab.adapters.Praktikan.PraktikanInformasiAdapter;
import com.leadevs.misslab.models.Informasi;

public class HomePraktikanActivity extends AppCompatActivity implements PraktikanInformasiAdapter.OnPraktikanInformasiListener {

    private RecyclerView PraktiaknRVInformasi;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("informations");
    private PraktikanInformasiAdapter praktikanInformasiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_praktikan);
        PraktiaknRVInformasi = findViewById(R.id.RVItemInformasiPraktikan);
        setUpRecycleView();
    }

    public void setUpRecycleView() {
        Query query = collectionReference.whereEqualTo("to", "Praktikan").orderBy("created_at", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Informasi> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Informasi>()
                .setQuery(query, Informasi.class)
                .build();
        praktikanInformasiAdapter = new PraktikanInformasiAdapter(firestoreRecyclerOptions, this);
        PraktiaknRVInformasi.setLayoutManager(new LinearLayoutManager(this));
        PraktiaknRVInformasi.setAdapter(praktikanInformasiAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        praktikanInformasiAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        praktikanInformasiAdapter.stopListening();
    }




    @Override
    public void onPraktikanInformasiClick(DocumentSnapshot documentSnapshot, int positition) {
        Informasi informasi = documentSnapshot.toObject(Informasi.class);
        Intent intent = new Intent(this, DetailInformasi.class);
        intent.putExtra("title", informasi.getTitle());
        intent.putExtra("content", informasi.getContent());
        startActivity(intent);
    }
}