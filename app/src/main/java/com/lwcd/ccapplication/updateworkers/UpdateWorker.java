package com.lwcd.ccapplication.updateworkers;

import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Data;
import androidx.work.Worker;

public class UpdateWorker extends Worker {


    private final String TAG = "UpdateWorker";

    @NonNull
    @Override
    public WorkerResult doWork() {

        Log.i(TAG, "doWork");

        System.out.println("dowork");

        boolean isUpdate = this.getInputData().getBoolean("key_update", false);

        Log.e(TAG, "doWork: update:" + isUpdate);

        if (isUpdate) {

            try {
                Thread.sleep(5000);//模拟长时间工作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.setOutputData(new Data.Builder().putString("key_update_result", "update result").build());
            return WorkerResult.SUCCESS;
        }else {
            return WorkerResult.FAILURE;
        }
    }
}
