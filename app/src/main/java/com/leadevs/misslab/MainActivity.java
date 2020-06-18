package com.leadevs.misslab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {
    SpaceNavigationView spaceNavigationView;
    Fragment absenFragment, praktikumFragment, homeFragment, pengajarFragment, infomasiFragment;
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
                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void goJadwalScreen(View view) {
        setFragment(absenFragment);
    }
}
