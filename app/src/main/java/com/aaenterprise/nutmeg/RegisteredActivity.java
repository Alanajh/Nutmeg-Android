package com.aaenterprise.nutmeg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisteredActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mUser;
    EditText mPassword;
    EditText mTxtCnfPassword;
    Button mBtnCnf;
    TextView mMainView, mNotMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        db = new DatabaseHelper(this);
        mUser = (EditText)findViewById(R.id.user_name);
        mPassword = (EditText)findViewById(R.id.password);
        mTxtCnfPassword = (EditText)findViewById(R.id.cnf_password);
        mBtnCnf = (Button)findViewById(R.id.btn_login);
        mMainView = (TextView)findViewById(R.id.yes_reg);
        mNotMainView = (TextView)findViewById(R.id.not_reg);
        mNotMainView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisteredActivity.this, " Alana ",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mMainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity = new Intent(RegisteredActivity.this,
                        com.aaenterprise.nutmeg.MainActivity.class);
                startActivity(MainActivity);
            }
        });


        mBtnCnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUser.getText().toString().trim();
                String pwd = mPassword.getText().toString().trim();
                String cnfPwd = mTxtCnfPassword.getText().toString().trim();

                if(pwd.equals(cnfPwd)){
                    long val = db.addUser(user, pwd);
                    if (val > 0) {
                        Toast.makeText(RegisteredActivity.this, " You have registered.",
                                Toast.LENGTH_SHORT).show();
                        Intent openLogin = new Intent(RegisteredActivity.this,
                                MainActivity.class);
                        startActivity(openLogin);
                    }else{
                        Toast.makeText(RegisteredActivity.this, " Registration Error.",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegisteredActivity.this, " The passwords do not match.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void launchRegistered(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}