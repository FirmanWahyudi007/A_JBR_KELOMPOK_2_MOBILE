package com.example.temanautis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.temanautis.Model.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TabActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        sessionManager = new SessionManager(TabActivity.this);
        if (sessionManager.isLoggedIn() == false){
            moveToLogin();
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new Fragment1()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){

                    case R.id.nav_home:
                        fragment = new Fragment1();
                        break;
                    case R.id.nav_donasi:
                        fragment = new Fragment2();
                        break;
                    case R.id.nav_profile:
                        fragment = new Fragment3();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });

    }
    private void moveToLogin(){
        Intent intent = new Intent(TabActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}