package com.example.moveintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MoveWithDataActivity extends AppCompatActivity {

    public static String EXTRA_NAME = "extra_name";
    public static String EXTRA_AGE = "extra_age";
    TextView tv_name,tv_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_data);

        initial();

        String Name = getIntent().getStringExtra(EXTRA_NAME);
        int Age = getIntent().getIntExtra(EXTRA_AGE,0);

        tv_name.setText("Name : "+Name);
        tv_age.setText("Age : "+Age);

    }

    private void initial(){
        tv_name = findViewById(R.id.tvName);
        tv_age = findViewById(R.id.tvAge);
    }
}
