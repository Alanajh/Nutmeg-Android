package com.aaenterprise.nutmeg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.view.View;

public class Troubleshooting extends AppCompatActivity {

    DatabaseHelper db;
    Switch sConnection;
    EditText path;
    EditText name;
    String dbName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troubleshooting);

        db = new DatabaseHelper(this);
        sConnection = findViewById(R.id.dbConnect_switch);
        path = findViewById(R.id.multilinedbPath);
        name = findViewById(R.id.multilinedbNam);
        dbName = db.getDatabaseName();

        if(db.connectt = true){
            sConnection.setChecked(true);
            path.setText(db.getPath());
            name.setText(dbName);
        }else{
            sConnection.setChecked(false);
        }
    db.close();
    }
    public void goHome(View v){
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
    }
}