package com.lwcd.ccapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lwcd.ccapplication.updateworkers.UpdateEngine;

import java.util.UUID;

import androidx.work.WorkManager;
import androidx.work.WorkStatus;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MyViewModel viewModel;
    private String uuid;
    private LifecycleOwner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getCity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String city) {
                Toast.makeText(MainActivity.this, "数据发送改变: city:" + city, Toast.LENGTH_SHORT).show();
            }
        });

//        owner =

        findViewById(R.id.btn_set_work).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick");
                UpdateEngine engine = new UpdateEngine();
                uuid = engine.scheduleUpdate();

                if (uuid != null && uuid.length() > 0) {
                    WorkManager.getInstance().getStatusById(UUID.fromString(uuid))
                            .observe(MainActivity.this, new Observer<WorkStatus>() {
                                @Override
                                public void onChanged(@Nullable WorkStatus workStatus) {
                                    String result = workStatus.getOutputData().getString("key_update_result", "");
                                    System.out.println(result);
                                }
                            });
                }


            }
        });

        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCity();
            }
        });


    }

    private void SetCity(){
        viewModel.getCity().setValue("XiaM");
    }
}
