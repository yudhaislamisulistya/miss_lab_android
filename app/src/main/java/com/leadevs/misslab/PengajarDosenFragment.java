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
import android.widget.Toast;

import com.leadevs.misslab.adapters.DosenAdapter;
import com.leadevs.misslab.adapters.DosenGridViewAdapter;
import com.leadevs.misslab.models.Asisten;
import com.leadevs.misslab.models.Dosen;

import java.util.ArrayList;
import java.util.List;

public class PengajarDosenFragment extends Fragment {
    GridView gridView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_pengajar_dosen, container, false);
        gridView = root.findViewById(R.id.GVDosen);
        final List<Dosen> daftarAsisten = getDataDosen();
        DosenGridViewAdapter dosenAdapter = new DosenGridViewAdapter(getContext(), daftarAsisten);
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

    private ArrayList<Dosen> getDataDosen(){
        ArrayList<Dosen> dosens = new ArrayList<>();
        dosens.add(new Dosen(1, "Yudha Islami Sulistya", "0909012220", "Laki - Laki", "085340472927", "https://facebook.com/adsad"));
        dosens.add(new Dosen(2, "Huzain Azis, S.Kom, M.Cs", "0909042434", "Laki - Laki", "08662323233", "https://facebook.com/adsad"));
        dosens.add(new Dosen(3, "Abdul Rachman Manga, S.Kom, M.Eng", "0992392933", "Laki - Laki", "088329382398", "https://facebook.com/adsad"));
        dosens.add(new Dosen(4, "Lilis Nurhayati, S.Kom, M.Eng", "0992392933", "Laki - Laki", "088329382398", "https://facebook.com/adsad"));        dosens.add(new Dosen(1, "Yudha Islami Sulistya", "0909012220", "Laki - Laki", "085340472927", "https://facebook.com/adsad"));
        dosens.add(new Dosen(2, "Huzain Azis, S.Kom, M.Cs", "0909042434", "Laki - Laki", "08662323233", "https://facebook.com/adsad"));
        dosens.add(new Dosen(3, "Abdul Rachman Manga, S.Kom, M.Eng", "0992392933", "Laki - Laki", "088329382398", "https://facebook.com/adsad"));
        dosens.add(new Dosen(4, "Lilis Nurhayati, S.Kom, M.Eng", "0992392933", "Laki - Laki", "088329382398", "https://facebook.com/adsad"));
        return dosens;
    }
}