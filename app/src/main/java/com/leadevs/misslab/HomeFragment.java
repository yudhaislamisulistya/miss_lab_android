package com.leadevs.misslab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leadevs.misslab.adapters.AsistenAdapter;
import com.leadevs.misslab.adapters.DosenAdapter;
import com.leadevs.misslab.adapters.PraktikumHomeAdapter;
import com.leadevs.misslab.models.Asisten;
import com.leadevs.misslab.models.Dosen;
import com.leadevs.misslab.models.Praktikum;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView RVPraktikum, RVDosen, RVAsisten;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_home, container, false);
        RVPraktikum = root.findViewById(R.id.RVItemPraktikum);
        RVDosen = root.findViewById(R.id.RVItemDosen);
        RVAsisten = root.findViewById(R.id.RVItemAsisten);

        List<Praktikum> daftarPraktikum = getDataPraktikum();

        PraktikumHomeAdapter praktikumHomeAdapter = new PraktikumHomeAdapter(getContext(),daftarPraktikum);
        RVPraktikum.setAdapter(praktikumHomeAdapter);
        RVPraktikum.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        List<Dosen> daftarDosen = getDataDosen();

        DosenAdapter dosenAdapter = new DosenAdapter(getContext(),daftarDosen);
        RVDosen.setAdapter(dosenAdapter);
        RVDosen.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        List<Asisten> daftarAsisten = getDataAsisten();

        AsistenAdapter asistenAdapter = new AsistenAdapter(getContext(),daftarAsisten);
        RVAsisten.setAdapter(asistenAdapter);
        RVAsisten.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        return root;
    }

    private ArrayList<Praktikum> getDataPraktikum(){
        ArrayList<Praktikum> praktikums = new ArrayList<>();
        praktikums.add(new Praktikum("Pemrograman Java Lanjut"));
        praktikums.add(new Praktikum("Pemrograman Visual"));
        praktikums.add(new Praktikum("Mobile Programming"));
        return praktikums;
    }

    private ArrayList<Dosen> getDataDosen(){
        ArrayList<Dosen> dosens = new ArrayList<>();
        dosens.add(new Dosen(1, "Yudha Islami Sulistya", "0909012220", "Laki - Laki", "085340472927", "https://facebook.com/adsad"));
        dosens.add(new Dosen(2, "Huzain Azis, S.Kom, M.Cs", "0909042434", "Laki - Laki", "08662323233", "https://facebook.com/adsad"));
        dosens.add(new Dosen(3, "Abdul Rachman Manga, S.Kom, M.Eng", "0992392933", "Laki - Laki", "088329382398", "https://facebook.com/adsad"));
        dosens.add(new Dosen(4, "Lilis Nurhayati, S.Kom, M.Eng", "0992392933", "Laki - Laki", "088329382398", "https://facebook.com/adsad"));
        return dosens;
    }

    private ArrayList<Asisten> getDataAsisten(){
        ArrayList<Asisten> asistens = new ArrayList<>();

        asistens.add(new Asisten(1, "La Saiman", "13020180214", "Aktif", "Laki - Laki", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        asistens.add(new Asisten(2, "Kasmira", "13020180214", "Aktif", "Perempuan", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        asistens.add(new Asisten(3, "Muhamad Trisnandar", "13020180214", "Aktif", "Laki - Laki", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        asistens.add(new Asisten(4, "Ericha Apriliyani", "13020180214", "Aktif", "Laki - Laki", "Jl. Pulau Papan", "08343434", "https://yudhaislamisulistya.com/gambar.jpg"));
        return asistens;
    }

}