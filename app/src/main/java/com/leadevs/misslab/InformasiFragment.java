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
import com.leadevs.misslab.adapters.InformasiAdapter;
import com.leadevs.misslab.models.Asisten;
import com.leadevs.misslab.models.Informasi;
import com.leadevs.misslab.models.Praktikum;

import java.util.ArrayList;
import java.util.List;

public class InformasiFragment extends Fragment {

    private RecyclerView RVInformasi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_informasi, container, false);
        RVInformasi = root.findViewById(R.id.RVItemInformasi);

        List<Informasi> daftarInformasi = getDataAsisten();

        InformasiAdapter informasiAdapter = new InformasiAdapter(getContext(),daftarInformasi);
        RVInformasi.setAdapter(informasiAdapter);
        RVInformasi.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return root;
    }

    private ArrayList<Informasi> getDataAsisten(){
        ArrayList<Informasi> informasis = new ArrayList<>();
        informasis.add(new Informasi(1, "Pembayaran Lab", "Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem Lorem ", "Asisten", "20 Februari 2020", "21 Maret 2020"));
        return informasis;
    }


}