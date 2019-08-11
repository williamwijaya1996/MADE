package com.example.mysound;

import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnSound;
    SoundPool sp;
    int soundId;
    boolean spLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSound = findViewById(R.id.btn_soundpool);

        sp = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();

        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if(status ==0){
                    spLoaded = true;
                }else {
                    Toast.makeText(MainActivity.this, "Gagal load", Toast.LENGTH_SHORT).show();
                }
            }
        });

        soundId = sp.load(this,R.raw.clinking_glasses,1);

        btnSound.setOnClickListener(myListener);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(spLoaded){
                sp.play(soundId,1,1,0,0,1);
            }
        }
    };
}

//    Parameter soundID merupakan id dari audio yang Anda muat.
//        LeftVolume dan RightVolume merupakan parameter float dari besar kecilnya volume yang range-nya dimulai dari 0.0 - 1.0.
//        Priority merupakan urutan prioritas. Semakin besar nilai priority, semakin tinggi prioritas audio itu untuk dijalankan.
//        Paremeter loop digunakan untuk mengulang audio ketika telah selesai dimainkan. Nilai -1 menunjukkan bahwa audio akan diulang-ulang tanpa berhenti. Nilai 0 menunjukkan audio akan dimainkan sekali. Nilai 1 menunjukkan audio akan dimainkan sebanyak 2 kali.
//        Parameter rate mempunyai range dari 0.5 - 2.0. Rate 1.0 akan memainkan audio secara normal, 0.5 akan memainkan audio dengan kecepatan setengah, dan 2.0 akan memainkan audio 2 kali lipat lebih cepat.
