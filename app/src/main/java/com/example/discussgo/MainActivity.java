package com.example.discussgo;

import android.graphics.PorterDuff;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            MenuItem otherItem;
            switch (item.getItemId()) {
                case R.id.navigationLogin:
                    Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.navigationRegister:
                    Toast.makeText(MainActivity.this, "Register", Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ConstraintLayout cL = findViewById(R.id.constraintContent);
//        cL.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
