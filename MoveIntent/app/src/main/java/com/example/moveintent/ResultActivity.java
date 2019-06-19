package com.example.moveintent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ResultActivity extends AppCompatActivity {

    RadioButton RB200,RB500;
    Button btnSend;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();

        RB200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               result = 200;
            }
        });

        RB500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 500;
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result>0) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", result);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            }
        });
    }

    private void init(){
        RB200 = findViewById(R.id.rb_200);
        RB500 = findViewById(R.id.rb_500);
        btnSend = findViewById(R.id.send);
    }
}
