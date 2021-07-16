package com.example.hospitalmanagementsystem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;



public class BottomNavigation extends AppCompatActivity {
   // Fragment current_fragment;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        BottomNavigationView bottomNav= findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if(savedInstanceState == null){
            bottomNav.setSelectedItemId(R.id.home);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()){
                case R.id.corona:
                    CoronavirusFragment fragment1 = new CoronavirusFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flFragment,fragment1);
                    ft.commit();
                    return true;
                case R.id.home:
                    HomeFragment homeFragment = new HomeFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flFragment,homeFragment);
                    ft.commit();
                    return true;
                case R.id.settings:
                    SettingsFragment settingsFragment = new SettingsFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flFragment,settingsFragment);
                    ft.commit();
                    return true;


            }
            return false;
        }
    };
}
