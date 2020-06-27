package com.leadevs.misslab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailInformasi extends AppCompatActivity {

    private TextView TVTitleDetailInformasi;
    private TextView TVContentDetailInformasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_informasi);

        TVTitleDetailInformasi = findViewById(R.id.TVTitleDetailInformasi);
        TVContentDetailInformasi = findViewById(R.id.TVContentDetailInformasi);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        TVTitleDetailInformasi.setText(title);
        TVContentDetailInformasi.setText(content);
    }
}