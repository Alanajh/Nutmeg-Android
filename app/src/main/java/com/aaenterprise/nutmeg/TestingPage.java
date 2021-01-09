package com.aaenterprise.nutmeg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class TestingPage extends AppCompatActivity {
    DatabaseHelper db;
    Integer x = 0;
    Integer d = 0;
    Integer change = 0;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_page);
        db = new DatabaseHelper(TestingPage.this);

        // Simple array with a list of tests
        ArrayAdapter mAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, db.getAllTests4());

        // ListViews display data in a scrollable list
        ListView theListView = findViewById(R.id.testListView);

        // Tells the ListView what data to use
        theListView.setAdapter(mAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                x++;
                String f = theListView.getItemAtPosition(5).toString();
                String testPicked = adapterView.getItemAtPosition(i).toString();

                if(testPicked == f){
                    Toast.makeText(TestingPage.this, testPicked, Toast.LENGTH_SHORT).show();
                    testingPage(view);
                }

            }
        });
    }

    public void testingPage(View v){
        Intent tests = new Intent(this, takeTest.class);
        startActivity(tests);
    }
}