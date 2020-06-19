package com.leadevs.misslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.leadevs.misslab.adapters.AsistenGridViewAdapter;
import com.leadevs.misslab.adapters.PraktikanGridViewAdapter;
import com.leadevs.misslab.models.Asisten;
import com.leadevs.misslab.models.Praktikan;

import java.util.ArrayList;
import java.util.List;

public class DetailPraktikum extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_praktikum);
        gridView = findViewById(R.id.GVPraktikan);
        final List<Praktikan> daftarAsisten = getDataAsisten();
        PraktikanGridViewAdapter praktikanGridViewAdapter = new PraktikanGridViewAdapter(getApplicationContext(), daftarAsisten);
        gridView.setAdapter(praktikanGridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), DetailPengajarAsisten.class));
            }
        });
    }

    private ArrayList<Praktikan> getDataAsisten(){
        ArrayList<Praktikan> praktikans = new ArrayList<>();
        praktikans.add(new Praktikan(1, "13020170214", "Andi Basso", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Lala", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Gon", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Aku", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Biji", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Haiii", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        praktikans.add(new Praktikan(1, "13020170214", "Cuaks", "Teknik Informatika", "2017", "085340472927", "Jl. Barawaja"));
        return praktikans;
    }
}