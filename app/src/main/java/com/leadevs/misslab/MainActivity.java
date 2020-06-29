package com.leadevs.misslab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    SpaceNavigationView spaceNavigationView;
    Fragment absenFragment, praktikumFragment, homeFragment, pengajarFragment, infomasiFragment;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReferenceActiveLab = db.collection("actives_lab");
    String uuid;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        absenFragment = new AbsenFragment();
        praktikumFragment = new PraktikumFragment();
        homeFragment = new HomeFragment();
        pengajarFragment = new PengajarFragment();
        infomasiFragment = new InformasiFragment();
        setFragment(homeFragment);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_today_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_meeting_room_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_people_alt_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_all_inbox_24));
        new Notification();



        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                setFragment(homeFragment);
                spaceNavigationView.setCentreButtonSelectable(true);
            }

            @Override
            public void onItemClick(final int itemIndex, String itemName) {
                switch (itemIndex){
                    case 0:
                        setFragment(absenFragment);
                        return;
                    case 1:
                        setFragment(praktikumFragment);
                        return;
                    case 2:
                        setFragment(pengajarFragment);
                        return;
                    case 3:
                        setFragment(infomasiFragment);
                        return;
                }
            }
            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Informasi")
                .setMessage("Apakah Anda Mau Keluar Dari Aplikasi MISS Lab ? Status Aktif Lab Akan Berubah Ke Tidak Aktif")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateStatusActiveLabAsisten(getDocumentIdActiveLab(user.getUid()), "Tidak Ada");
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void updateStatusActiveLabAsisten(String id, String status) {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("status_active", status);

        collectionReferenceActiveLab
                .document(id)
                .set(statusMap, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getBaseContext(), "Status Berhasil Diubah",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getBaseContext(), "Status Gagal Diubah",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public String getDocumentIdActiveLab(String id) {
        collectionReferenceActiveLab
                .whereEqualTo("id", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                uuid = document.getId();
                            }
                        } else {
                            System.out.println(task.getException());
                        }
                    }
                });
        return uuid;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void goJadwalScreen(View view) {
        setFragment(absenFragment);
    }

    public void getRuanganScreen(View view) {
        setFragment(praktikumFragment);
    }

    public void getPengajarScreen(View view) {
        setFragment(pengajarFragment);
    }

    public void getInformasiScreen(View view) {
        setFragment(infomasiFragment);
    }

    public void goNotificationScreen(View view){
        startActivity(new Intent(this, NotificationActivity.class));
    }

    public void goLoginScreen(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, Login.class));
        finish();
    }


}
