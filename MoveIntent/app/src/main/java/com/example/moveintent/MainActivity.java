package com.example.moveintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moveintent.Model.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button moveIntent, moveWithData, moveWithObject, dialNum, moveWithResult;
    int RequestCode = 1;
    TextView tvResult;

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == RequestCode){
            if(resultCode == Activity.RESULT_OK){
                tvResult.setText(data.getStringExtra("result")+"");
            }
        }
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
                Person person = new Person();
                person.setName("William");
                person.setAge(22);
                person.setEmail("William@gmail.com");
                person.setCity("Jakarta");
                break;
            case R.id.btn_phone:
                String phoneNumber = "081219527772";
                Intent phoneDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+phoneNumber));
                startActivity(phoneDial);
                break;
            case R.id.activity_with_result:
                Intent withResult = new Intent(this,ResultActivity.class);
                startActivityForResult(withResult,RequestCode);
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
        tvResult = findViewById(R.id.tv_result);
    }
}
