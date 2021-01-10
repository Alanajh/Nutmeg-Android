package com.aaenterprise.nutmeg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class takeTest extends AppCompatActivity {

    DatabaseHelper db;
    Button home_btn, previous_btn, next_btn;
    RadioButton a1, a2, a3, a4;
    TextView question_textview, count_textview;
    ArrayList<String> testQuestion, testAnswers;;
    int count;
    int i = 0;
    int grade = 0;
    int testLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);


        db = new DatabaseHelper(takeTest.this);
        count = 0;
        testQuestion = db.getQ();
        //testAnswers = db.getA();
        testLength = testQuestion.size();
        home_btn = findViewById(R.id.btn_home);
        previous_btn = findViewById(R.id.previous_btn);
        next_btn = findViewById(R.id.next_btn);
        question_textview = findViewById(R.id.question_textview);
        count_textview = findViewById(R.id.questionCount);

        a1 = findViewById(R.id.radioButton1);
        a2 = findViewById(R.id.radioButton2);
        a3 = findViewById(R.id.radioButton3);
        a4 = findViewById(R.id.radioButton4);

        // first test question
        // question_textview.setText(testQuestion.get(i));
        count_textview.setText("000 / " + testLength);

        previous_btn.setOnClickListener(v -> {
            question_textview.setText(testQuestion.get(i));
            count--;
            count_textview.setText(count + " / " + testLength);
            i--;
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            int x = 0;
            public void onClick(View v) {
                question_textview.setText(testQuestion.get(i));
                i++;
                count++;
                count_textview.setText(count + " / " + testLength);
                if (count == testLength){
                    testQuestion.add("Congrats this is the end of this test!");
                    count = testLength;
                }
                switch (x) {
                    case 0:
                        x++;
                        break;

                    case 10:
                        // Intent i= new Intent(v.getContext(), TestingPage.class);
                       // startActivity(i);
                        break;
                }
            }
        });
    }


    public void testingPage(View v){
        Intent tests = new Intent(this, TestingPage.class);
        startActivity(tests);
    }

}