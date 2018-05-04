package com.lwcd.ccapplication;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewModel viewModel;
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
    }

    public void SetCity(View view){
        viewModel.getCity().setValue("XiaM");
    }
}
