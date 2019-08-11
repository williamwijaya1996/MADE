package com.example.mypreloaddata.services;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mypreloaddata.R;
import com.example.mypreloaddata.database.MahasiswaHelper;
import com.example.mypreloaddata.model.MahasiswaModel;
import com.example.mypreloaddata.pref.AppPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class LoadDataAsync extends AsyncTask<Void, Integer, Boolean> {

    private final String TAG = LoadDataAsync.class.getSimpleName();
    private MahasiswaHelper mahasiswaHelper;
    private AppPreference appPreference;
    private WeakReference<LoadDataCallback> weakCallback;
    private WeakReference<Resources> weakResources;
    double progress;
    double maxprogress = 100;

    @Override
    protected void onPreExecute() {
        Log.e(TAG, "onPreExecute");
        weakCallback.get().onPreLoad();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        weakCallback.get().onProgressUpdate(values[0]);
    }


    @Override
    protected Boolean doInBackground(Void... voids) {
        Boolean firstRun = appPreference.getFirstRun();
        if (firstRun) {
            ArrayList<MahasiswaModel> mahasiswaModels = preLoadRaw();
            mahasiswaHelper.open();
            progress = 30;
            publishProgress((int) progress);
            Double progressMaxInsert = 80.0;
            Double progressDiff = (progressMaxInsert - progress) / mahasiswaModels.size();
            boolean isInsertSuccess;
            try {
                mahasiswaHelper.beginTransaction();
                for (MahasiswaModel model : mahasiswaModels) {

                    if (isCancelled()) {
                        break;
                    } else {
                        mahasiswaHelper.insertTransaction(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                }
                if (isCancelled()) {
                    isInsertSuccess = false;
                    appPreference.setFirstRun(true);
                    weakCallback.get().onLoadCancel();
                } else {
                    mahasiswaHelper.setTransactionSuccess();
                    isInsertSuccess = true;
                    appPreference.setFirstRun(false);
                }
            } catch (Exception e) {
                Log.e(TAG, "doInBackground: Exception");
                isInsertSuccess = false;
            } finally {
                mahasiswaHelper.endTransaction();
            }
            mahasiswaHelper.close();
            publishProgress((int) maxprogress);
            return isInsertSuccess;
        } else {
            try {
                synchronized (this) {
                    this.wait(2000);
                    publishProgress(50);
                    this.wait(2000);
                    publishProgress((int) maxprogress);
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            weakCallback.get().onLoadSuccess();
        } else {
            weakCallback.get().onLoadFailed();
        }
    }

    public LoadDataAsync(MahasiswaHelper mahasiswaHelper, AppPreference appPreference, LoadDataCallback weakCallback, Resources weakResources) {
        this.mahasiswaHelper = mahasiswaHelper;
        this.appPreference = appPreference;
        this.weakCallback = new WeakReference<>(weakCallback);
        this.weakResources = new WeakReference<>(weakResources);
    }

    private ArrayList<MahasiswaModel> preLoadRaw() {
        ArrayList<MahasiswaModel> mahasiswaModels = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = weakResources.get();
            InputStream raw_dict = res.openRawResource(R.raw.data_mahasiswa);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                MahasiswaModel mahasiswaModel;
                mahasiswaModel = new MahasiswaModel(splitstr[0], splitstr[1]);
                mahasiswaModels.add(mahasiswaModel);
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mahasiswaModels;
    }


}
