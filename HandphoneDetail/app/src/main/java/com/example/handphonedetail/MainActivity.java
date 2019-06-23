package com.example.handphonedetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.handphonedetail.Adapter.ListPhoneAdapter;
import com.example.handphonedetail.Adapter.PhoneItemListener;
import com.example.handphonedetail.Data.DataHandphone;
import com.example.handphonedetail.Model.Handphone;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Handphone> list = new ArrayList<>();
    ListPhoneAdapter adapter = new ListPhoneAdapter(list);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setActionBarTitle("Handphone");

        list.addAll(DataHandphone.getDataPhone());
        showRecyclerPhoneList();

    }

    private void init() {
        recyclerView = findViewById(R.id.rvParent);
        recyclerView.setHasFixedSize(true);

    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void showRecyclerPhoneList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setListHandphone(list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new PhoneItemListener(this, recyclerView,
                new PhoneItemListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        gotoDetail(position);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }

                }));
    }

    private void gotoDetail(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.POSITION, position);
        startActivity(intent);
    }
}
