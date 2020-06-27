package com.leadevs.misslab.ui.praktikan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.leadevs.misslab.R;
import com.leadevs.misslab.adapters.Praktikan.PraktikanActiveLabAsistenAdapter;
import com.leadevs.misslab.models.ActiveLab;

public class PraktikanActiveLabAsistenActivity extends AppCompatActivity implements PraktikanActiveLabAsistenAdapter.OnPraktikanActiveLabAsistneListener {

    private RecyclerView PraktikanRVActiveLabAsisten;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("actives_lab");
    private PraktikanActiveLabAsistenAdapter praktikanActiveLabAsistenAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praktikan_active_lab_asisten);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        PraktikanRVActiveLabAsisten = findViewById(R.id.RVItemActiveLabAsisten);
        setUpRecycleView();
    }
    public void setUpRecycleView() {
        Query query = collectionReference.orderBy("updated_at", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<ActiveLab> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<ActiveLab>()
                .setQuery(query, ActiveLab.class)
                .build();
        progressDialog.dismiss();
        praktikanActiveLabAsistenAdapter = new PraktikanActiveLabAsistenAdapter(firestoreRecyclerOptions, this);
        PraktikanRVActiveLabAsisten.setLayoutManager(new LinearLayoutManager(this));
        PraktikanRVActiveLabAsisten.setAdapter(praktikanActiveLabAsistenAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        praktikanActiveLabAsistenAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        praktikanActiveLabAsistenAdapter.stopListening();
    }

    @Override
    public void onPraktikanActiveLabAsistenClick(DocumentSnapshot documentSnapshot, int positition) {

    }
}