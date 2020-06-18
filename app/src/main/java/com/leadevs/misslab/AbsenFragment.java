package com.leadevs.misslab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leadevs.misslab.adapters.AbsenAdapter;
import com.leadevs.misslab.adapters.PraktikumAdapter;
import com.leadevs.misslab.models.Absen;
import com.leadevs.misslab.models.Praktikum;

import java.util.ArrayList;
import java.util.List;

public class AbsenFragment extends Fragment {
    RecyclerView RVAbsen;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_absen, container, false);
        RVAbsen = root.findViewById(R.id.RVItemAbsenHariIni);

        List<Absen> daftarAbsens = getDataAbsen();

        AbsenAdapter absenAdapter = new AbsenAdapter(getContext(),daftarAbsens);
        RVAbsen.setAdapter(absenAdapter);
        RVAbsen.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return root;
    }

    private ArrayList<Absen> getDataAbsen(){
        ArrayList<Absen> absens = new ArrayList<>();
        absens.add(new Absen(1, "Yudha Islami Sulistya", "Java Lanjut", "09.00 - 10.00", "Lab RPL", "Belum Absen"));
        return absens;
    }
}