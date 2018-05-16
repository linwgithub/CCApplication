package com.lwcd.ccapplication.updateworkers;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

public class UpdateEngine {

    private String TAG = "UpdateEngine";

    public String scheduleUpdate() {
        Log.e(TAG, "scheduleUpdate: ");
        WorkRequest updateWorkerRequest1 = new OneTimeWorkRequest.Builder(UpdateWorker.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setInputData(new Data.Builder()
                        .putBoolean("key_update", true)
                        .build())
                .build();

        WorkRequest updateWorkerRequest2 = new PeriodicWorkRequest.Builder(UpdateWorker.class, 900000, TimeUnit.MICROSECONDS)
//                .setPeriodStartTime()
                .setInputData(new Data.Builder()
                        .putBoolean("key_update", true)
                        .build())
                .build();

        WorkManager.getInstance().enqueue(updateWorkerRequest2);

        return updateWorkerRequest2.getId().toString();
    }
}
