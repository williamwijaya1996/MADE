package com.example.moveintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.moveintent.Model.Person;

public class MoveWithObjectActivity extends AppCompatActivity {

    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);

        tvText = findViewById(R.id.tv_text);

        Person person = new Person();

        String text = "Nama : "+person.getName()+"\nUmur : "+person.getAge()+
                "\nEmail :"+person.getEmail()+"\nKota :"+person.getCity();

        tvText.setText(text);
    }
}
