package com.leadevs.misslab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.leadevs.misslab.adapters.DetailPengajarAsistenAdapter;
import com.leadevs.misslab.adapters.InformasiAdapter;
import com.leadevs.misslab.models.DetailPengajar;
import com.leadevs.misslab.models.Informasi;

import java.util.ArrayList;
import java.util.List;

public class DetailPengajarDosen extends AppCompatActivity implements DetailPengajarAsistenAdapter.OnDetailPengajarListener {
    private RecyclerView RVDetailPengajar;
    ArrayList<DetailPengajar> detailPengajars = new ArrayList<>();
    private TextView fullname;
    private TextView phone;
    private TextView gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengajar_dosen);
        fullname = findViewById(R.id.TVDetailPDNamaLengkap);
        phone = findViewById(R.id.TVDetailPDNoTelp);
        gender = findViewById(R.id.TVDetailPDJenisKelamin);

        fullname.setText(getIntent().getStringExtra("fullname"));
        phone.setText(getIntent().getStringExtra("phone"));
        gender.setText(getIntent().getStringExtra("gender"));

        RVDetailPengajar = findViewById(R.id.RVItemDetailPengajarDosen);

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