package com.example.movieandtvshowcatalogue.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.example.movieandtvshowcatalogue.MainActivity;
import com.example.movieandtvshowcatalogue.R;
import com.example.movieandtvshowcatalogue.api.ApiInterface;
import com.example.movieandtvshowcatalogue.config.Constants;
import com.example.movieandtvshowcatalogue.model.MovieApi;
import com.example.movieandtvshowcatalogue.model.MovieApiResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationReleaseReceiver extends BroadcastReceiver {
    NotificationCompat.Builder mBuilder;
    public static String CHANNEL_ID = "channel_02";
    public static CharSequence CHANNEL_NAME = "release_channel";
    private final static String GROUP_KEY_EMAILS = "group_key_emails";
    private final String TAG = "mydebug_notifRelease";
    public ArrayList<MovieApi> movieListReleaseApi = new ArrayList<>();
    int idNotif = 0;
    int idMaxNotif = 8;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent repeating_intent = new Intent(context, MainActivity.class);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);


        callReleaseMovies(strDate, context);
    }

    private void callReleaseMovies(String date, final Context context) {
        idNotif=0;
        final Retrofit movieApi = new Retrofit.Builder().baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = movieApi.create(ApiInterface.class);
        Call<MovieApiResponse> callMovieRelease = apiInterface.apiReleaseMovie(Constants.KEY_API, date, date);
        callMovieRelease.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                Log.d("notification",response.code()+"");
                if (response.code() == 200 && response.isSuccessful()) {
                    movieListReleaseApi.addAll(response.body().getResults());
                    for (int i = 0; i < movieListReleaseApi.size(); i++) {
                        sendNotif(context);
                        idNotif++;
                        Log.d("notification", idNotif+"");
                    }

                }else{
                    Log.d(TAG,"response Failed");
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                Log.d(TAG,"response Failure");
            }
        });
    }

    private void sendNotif(Context context) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notifications_white_48px);
//
//        if (idNotif < idMaxNotif) {
            mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle(movieListReleaseApi.get(idNotif).getTitle())
                    .setContentText(movieListReleaseApi.get(idNotif).getTitle() + " Just release Now!")
                    .setSmallIcon(R.drawable.ic_notifications_white_48px)
                    .setLargeIcon(largeIcon)
                    .setGroup(GROUP_KEY_EMAILS)
                    .setAutoCancel(true);

//        } else {
//            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
//                    .addLine(movieListReleaseApi.get(idNotif).getTitle()+ " Just release Now!")
//                    .addLine(movieListReleaseApi.get(idNotif-1).getTitle()+ " Just release Now!")
//                    .setBigContentTitle(idNotif+" new Emails")
//                    .setSummaryText(movieListReleaseApi.get(idNotif).getTitle() + "just release now!");
//
//            mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setContentTitle(idNotif+"new Emails")
//                    .setContentText("Release Movie")
//                    .setSmallIcon(R.drawable.ic_notifications_white_48px)
//                    .setGroup(GROUP_KEY_EMAILS)
//                    .setGroupSummary(true)
//                    .setStyle(inboxStyle)
//                    .setAutoCancel(true);
//        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            mBuilder.setChannelId(CHANNEL_ID);

            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();

        if (mNotificationManager != null) {
            mNotificationManager.notify(idNotif, notification);
        }
    }
}
