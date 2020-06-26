package com.leadevs.misslab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.leadevs.misslab.adapters.PraktikumAdapter;
import com.leadevs.misslab.models.Praktikum;

import java.util.ArrayList;
import java.util.List;

public class PraktikumGanjilFragment extends Fragment implements PraktikumAdapter.OnPraktikumListener, AdapterView.OnItemSelectedListener {

    RecyclerView RVPraktikum;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_praktikum_ganjil, container, false);
        RVPraktikum = root.findViewById(R.id.RVItemPraktikumFP);
        spinner = root.findViewById(R.id.SHari);
        spinner.setOnItemSelectedListener(this);
        List<Praktikum> daftarPraktikum = getDataPraktikum();

        PraktikumAdapter praktikumAdapter = new PraktikumAdapter(getContext(),daftarPraktikum, this);
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

    @Override
    public void onPraktikumListener(int positition) {
        startActivity(new Intent(getContext(), DetailPraktikum.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}