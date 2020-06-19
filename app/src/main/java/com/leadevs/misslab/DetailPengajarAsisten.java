package com.leadevs.misslab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.leadevs.misslab.adapters.DetailPengajarAsistenAdapter;
import com.leadevs.misslab.adapters.InformasiAdapter;
import com.leadevs.misslab.models.DetailPengajar;
import com.leadevs.misslab.models.Informasi;

import java.util.ArrayList;
import java.util.List;

public class DetailPengajarAsisten extends AppCompatActivity implements DetailPengajarAsistenAdapter.OnDetailPengajarListener {
    private RecyclerView RVDetailPengajar;
    ArrayList<DetailPengajar> detailPengajars = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengajar_asisten);

        RVDetailPengajar = findViewById(R.id.RVItemDetailPengajarAsisten);

        List<DetailPengajar> daftarDetailPengajar = getDetailPengajar();

        DetailPengajarAsistenAdapter detailPengajarAsistenAdapter = new DetailPengajarAsistenAdapter(getApplicationContext(),daftarDetailPengajar, this);
        RVDetailPengajar.setAdapter(detailPengajarAsistenAdapter);
        RVDetailPengajar.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

    }

    private ArrayList<DetailPengajar> getDetailPengajar(){
        detailPengajars.clear();
        detailPengajars.add(new DetailPengajar(1,"Berhasil Absen", "Pemrograman Java Lanjut C1", "Senin, 08.00 - 09.40", "RPL", "Asisten 1"));
        detailPengajars.add(new DetailPengajar(2,"Berhasil Absen", "Pemrograman Java Lanjut C1", "Senin, 08.00 - 09.40", "RPL", "Asisten 1"));
        detailPengajars.add(new DetailPengajar(3,"Berhasil Absen", "Pemrograman Java Lanjut C1", "Senin, 08.00 - 09.40", "RPL", "Asisten 1"));
        detailPengajars.add(new DetailPengajar(4,"Berhasil Absen", "Pemrograman Java Lanjut C1", "Senin, 08.00 - 09.40", "RPL", "Asisten 1"));
        detailPengajars.add(new DetailPengajar(5,"Berhasil Absen", "Pemrograman Java Lanjut C1", "Senin, 08.00 - 09.40", "RPL", "Asisten 1"));
        return detailPengajars;
    }


    @Override
    public void onDetailPengajarListener(int positition) {
        startActivity(new Intent(getApplicationContext(), DetailPraktikum.class));
    }
}