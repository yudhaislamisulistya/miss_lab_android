package com.leadevs.misslab.ui.praktikan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.leadevs.misslab.DetailInformasi;
import com.leadevs.misslab.R;
import com.leadevs.misslab.adapters.Praktikan.PraktikanInformasiAdapter;
import com.leadevs.misslab.models.Informasi;

import java.util.ArrayList;
import java.util.List;

public class HomePraktikanActivity extends AppCompatActivity implements PraktikanInformasiAdapter.OnPraktikanInformasiListener {

    private RecyclerView RVInformasi;
    ArrayList<Informasi> informasis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_praktikan);

        RVInformasi = findViewById(R.id.RVItemInformasi);

        List<Informasi> daftarInformasi = getDataAsisten();

        PraktikanInformasiAdapter informasiAdapter = new PraktikanInformasiAdapter(getBaseContext(),daftarInformasi, this);
        RVInformasi.setAdapter(informasiAdapter);
        RVInformasi.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));

    }

    private ArrayList<Informasi> getDataAsisten(){
        informasis.clear();
        informasis.add(new Informasi(1, "Pembayaran Lab", "Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem ", "Asisten", "20 Februari 2020", "21 Maret 2020"));
        informasis.add(new Informasi(2, "Semester Pendek Lab", "Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem ", "Asisten", "20 Februari 2020", "21 Maret 2020"));
        return informasis;
    }

    @Override
    public void onPraktikanInformasiClick(int positition) {
        startActivity(new Intent(getBaseContext(), DetailInformasi.class));
    }
}