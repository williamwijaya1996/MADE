package com.example.movieandtvshowcatalogueClosing;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;

import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.movieandtvshowcatalogueClosing.receiver.NotificationReceiver;
import com.example.movieandtvshowcatalogueClosing.receiver.NotificationReleaseReceiver;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {


    String title;
    private final String TAG = "mydebug_reminderAct";
    private final String EXTRA_TITLE = "extra_title";
    public static final int NOTIFICATION_ID = 1;
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "dicoding channel";

    NotificationCompat.Builder mBuilder;
    NotificationManager mNotificationManager;
    Switch dailySwitch, releaseSwitch;
    AlarmManager alarmManager, alarmManagerRelease;
    PendingIntent pendingIntent, pendingIntentRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        if (savedInstanceState == null) {
            title = getResources().getString(R.string.reminder_options);
        } else {
            title = savedInstanceState.getString(EXTRA_TITLE);
        }


        initId();
        init();


    }

    private void initId() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.topColor)));
        } else {
            Log.d(TAG, "no Response");
        }
        dailySwitch = findViewById(R.id.switch_daily);
        releaseSwitch = findViewById(R.id.switch_release);

    }

    private void init() {


        dailySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, 7);
                    calendar.set(Calendar.MINUTE, 00);
                    calendar.set(Calendar.SECOND, 00);

                    Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);

                    pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                } else {
                    alarmManager.cancel(pendingIntent);
                }
            }
        });

        releaseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, 8);
                    calendar.set(Calendar.MINUTE,00);
                    calendar.set(Calendar.SECOND,00);

                    Intent intent = new Intent(getApplicationContext(), NotificationReleaseReceiver.class);

                    pendingIntentRelease = PendingIntent.getBroadcast(getApplicationContext(), 101, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManagerRelease = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManagerRelease.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntentRelease);
                } else {
                    alarmManagerRelease.cancel(pendingIntentRelease);
                }
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_TITLE, title);
    }
}
