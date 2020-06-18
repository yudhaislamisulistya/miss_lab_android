package com.leadevs.misslab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leadevs.misslab.adapters.PraktikumAdapter;
import com.leadevs.misslab.adapters.PraktikumHomeAdapter;
import com.leadevs.misslab.models.Praktikum;

import java.util.ArrayList;
import java.util.List;


public class PraktikumFragment extends Fragment {

    RecyclerView RVPraktikum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_praktikum, container, false);
        RVPraktikum = root.findViewById(R.id.RVItemPraktikumFP);

        List<Praktikum> daftarPraktikum = getDataPraktikum();

        PraktikumAdapter praktikumAdapter = new PraktikumAdapter(getContext(),daftarPraktikum);
        RVPraktikum.setAdapter(praktikumAdapter);
        RVPraktikum.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return root;
    }

    private ArrayList<Praktikum> getDataPraktikum(){
        ArrayList<Praktikum> praktikums = new ArrayList<>();
        praktikums.add(new Praktikum("Pemrograman Java Lanjut"));
        praktikums.add(new Praktikum("Pemrograman Visual"));
        praktikums.add(new Praktikum("Mobile Programming"));
        return praktikums;
    }
}