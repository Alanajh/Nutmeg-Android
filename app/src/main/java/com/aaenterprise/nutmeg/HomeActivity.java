package com.aaenterprise.nutmeg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void testingPage(View v){
        Intent tests = new Intent(this, TestingPage.class);
        startActivity(tests);
    }
    public void troubleshooting(View v){
        Intent t = new Intent(this, Troubleshooting.class);
        startActivity(t);
    }
}