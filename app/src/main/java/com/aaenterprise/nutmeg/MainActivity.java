package com.aaenterprise.nutmeg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText mUser;
    EditText mPassword;
    Button btn_Login;
    TextView mRegister;
    TextView mNotMainView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mUser = (EditText)findViewById(R.id.user_name);
        mPassword = (EditText)findViewById(R.id.password);
        btn_Login = (Button)findViewById(R.id.btn_login);
        mRegister = (TextView)findViewById(R.id.yes_reg);
        mNotMainView = (TextView)findViewById(R.id.not_reg);
        mNotMainView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, " Alana ",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent regIntent = new Intent(MainActivity.this, RegisteredActivity.class);
               // startActivity(regIntent);
            }
        });
    }
    public void loginBtn(View v) {
        String user = mUser.getText().toString().trim();
        String pwd = mPassword.getText().toString().trim();
        Boolean res = db.checkUser(user, pwd);

        String x = db.learn();
        ////// TEST = IF USER AND PASSWORD EQUAL, THEN LOG IN = TEST //////////////////////
        if(user.equals(pwd)) {
            Toast.makeText(MainActivity.this, x + " Successfully logged into testing.",
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(MainActivity.this, " The was an error when logging in.",
                    Toast.LENGTH_SHORT).show();
        }
        //db.close();
    }
}