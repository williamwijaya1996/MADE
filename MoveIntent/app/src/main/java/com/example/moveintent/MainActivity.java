package com.example.moveintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button moveIntent, moveWithData, moveWithObject, dialNum, moveWithResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        moveIntent.setOnClickListener(this);
        moveWithData.setOnClickListener(this);
        moveWithObject.setOnClickListener(this);
        dialNum.setOnClickListener(this);
        moveWithResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_move :
                Intent moveIntent = new Intent(this,MoveActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_move_with_data:
                Intent moveWithData = new Intent(this,MoveWithDataActivity.class);
                moveWithData.putExtra(MoveWithDataActivity.EXTRA_NAME,"DicodingAcademy Boy");
                moveWithData.putExtra(MoveWithDataActivity.EXTRA_AGE,5);
                startActivity(moveWithData);
                break;
            case R.id.btn_move_with_object:
                break;
            case R.id.btn_phone:
                break;
            case R.id.activity_with_result:
                break;
                default:
        }
    }

    private void initial(){
        moveIntent = findViewById(R.id.btn_move);
        moveWithData = findViewById(R.id.btn_move_with_data);
        moveWithObject = findViewById(R.id.btn_move_with_object);
        dialNum = findViewById(R.id.btn_phone);
        moveWithResult = findViewById(R.id.activity_with_result);

    }
}
