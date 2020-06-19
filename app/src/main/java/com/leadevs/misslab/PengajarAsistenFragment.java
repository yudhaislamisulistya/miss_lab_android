package com.leadevs.misslab;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.leadevs.misslab.adapters.AsistenGridViewAdapter;
import com.leadevs.misslab.adapters.DosenGridViewAdapter;
import com.leadevs.misslab.models.Asisten;
import com.leadevs.misslab.models.Dosen;

import java.util.ArrayList;
import java.util.List;

public class PengajarAsistenFragment extends Fragment {

    GridView gridView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_pengajar_dosen, container, false);
        gridView = root.findViewById(R.id.GVDosen);
        final List<Asisten> daftarAsisten = getDataAsisten();
        AsistenGridViewAdapter dosenAdapter = new AsistenGridViewAdapter(getContext(), daftarAsisten);
        gridView.setAdapter(dosenAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(daftarAsisten.get(position).getNamaLengkap());
                startActivity(new Intent(getContext(), DetailPengajarAsisten.class));
            }
        });
        return root;
    }

    private ArrayList<Asisten> getDataAsisten(){
        ArrayList<Asisten> asistens = new ArrayList<>();
        asistens.add(new Asisten(1, "La Saiman", "13020180214", "Aktif", "Laki - Laki", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        asistens.add(new Asisten(2, "Kasmira", "13020180214", "Aktif", "Perempuan", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        asistens.add(new Asisten(3, "Muhamad Trisnandar", "13020180214", "Aktif", "Laki - Laki", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        asistens.add(new Asisten(4, "Ericha Apriliyani", "13020180214", "Aktif", "Laki - Laki", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));        asistens.add(new Asisten(1, "La Saiman", "13020180214", "Aktif", "Laki - Laki", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        asistens.add(new Asisten(2, "Kasmira", "13020180214", "Aktif", "Perempuan", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        asistens.add(new Asisten(3, "Muhamad Trisnandar", "13020180214", "Aktif", "Laki - Laki", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        asistens.add(new Asisten(4, "Ericha Apriliyani", "13020180214", "Aktif", "Laki - Laki", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        return asistens;
    }
}