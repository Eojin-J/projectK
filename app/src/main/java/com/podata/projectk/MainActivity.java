package com.podata.projectk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.podata.projectk.ui.dashboard.DashboardFragment;
import com.podata.projectk.ui.home.HomeFragment;
import com.podata.projectk.ui.notifications.NotificationsFragment;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Fragment home, dash, noti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);




        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupWithNavController(binding.navView, navController);


        //바텀 네비게이션에 따른 변환
//        getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_activity_main, new HomeFragment()).commit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        home = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main,home).commit();


        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if(home == null) {
                            home = new HomeFragment();
                            fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main,home).commit();
                        }
                        else
                            fragmentManager.beginTransaction().show(home).commit();
                        if (dash!= null) fragmentManager.beginTransaction().hide(dash).commit();
                        if (noti!= null) fragmentManager.beginTransaction().hide(noti).commit();

                        window.setStatusBarColor(Color.parseColor("#ffffff"));
                        return true;
                    case R.id.navigation_dashboard:
                        if(dash == null) {
                            dash = new DashboardFragment();
                            fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main,dash).commit();
                        }
                        else
                            fragmentManager.beginTransaction().show(dash).commit();
                        if (home!= null) fragmentManager.beginTransaction().hide(home).commit();
                        if (noti!= null) fragmentManager.beginTransaction().hide(noti).commit();
                        window.setStatusBarColor(Color.parseColor("#FFB74D"));

                        return true;
                    case R.id.navigation_notifications:
                        if(noti == null) {
                            noti = new NotificationsFragment();
                            fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main,noti).commit();
                        }
                        else
                            fragmentManager.beginTransaction().show(noti).commit();
                        if (home!= null) fragmentManager.beginTransaction().hide(home).commit();
                        if (dash!= null) fragmentManager.beginTransaction().hide(dash).commit();
                        window.setStatusBarColor(Color.parseColor("#ffffff"));
                        return true;

                    default:
                        return false;
                }
            }
        });
    }




}