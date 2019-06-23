package com.example.handphonedetail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handphonedetail.Data.DataHandphone;
import com.example.handphonedetail.Model.Handphone;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String POSITION = "position";
    private ArrayList<Handphone> viewList = new ArrayList<>();
    TextView tvName, tvPrice,tvOs,tvOsV,tvSim,tvCpu,tvKcpu,tvStorage,tvRam,tvUkuran,tvDesc,tvExStorage,tvBattery;
    ImageView iPhoto;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();


        String name = viewList.get(position).getNama();
        String price = viewList.get(position).getHarga();
        String photo = viewList.get(position).getPhoto();
        String os = viewList.get(position).getOs();
        String osVer = viewList.get(position).getOsVer();
        String sim = viewList.get(position).getSim();
        String cpu = viewList.get(position).getCPU();
        String kCpu = viewList.get(position).getkCPU();
        String storage = viewList.get(position).getStorage();
        String ram = viewList.get(position).getRam();
        String ukuran = viewList.get(position).getUkuran();
        String desc = viewList.get(position).getDesc();
        String exStorage = viewList.get(position).getExStorage();
        String battery = viewList.get(position).getBattery();


        tvName.setText(name);
        tvPrice.setText(price);
        Picasso.with(getBaseContext())
                .load(photo)
                .into(iPhoto);
        tvOs.setText(os);
        tvOsV.setText(osVer);
        tvCpu.setText(cpu);
        tvKcpu.setText(kCpu);
        tvUkuran.setText(ukuran);
        tvSim.setText(sim);
        tvStorage.setText(storage);
        tvDesc.setText(desc);
        tvRam.setText(ram);
        tvBattery.setText(battery);
        tvExStorage.setText(exStorage);
    }

    private void init() {

        tvName = findViewById(R.id.tv_name);
        tvPrice = findViewById(R.id.tv_price);
        iPhoto = findViewById(R.id.img_photo);
        tvOs = findViewById(R.id.tv_os);
        tvOsV = findViewById(R.id.tv_os_ver);
        tvCpu = findViewById(R.id.tv_cpu);
        tvKcpu = findViewById(R.id.tv_kecepatan);
        tvUkuran = findViewById(R.id.tv_ukuran);
        tvSim = findViewById(R.id.tv_sim);
        tvStorage = findViewById(R.id.tv_storage);
        tvDesc = findViewById(R.id.tv_description);
        tvRam = findViewById(R.id.tv_ram);
        tvExStorage = findViewById(R.id.tv_exStorage);
        tvBattery = findViewById(R.id.tv_battery);



        viewList.addAll(DataHandphone.getDataPhone());
        position = getIntent().getIntExtra(POSITION, 0);
    }

}
