package com.lwcd.ccapplication;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends ViewModel {

    private MutableLiveData<List<User>> users;
    private MutableLiveData<String> city;


    public MutableLiveData<String> getCity() {
        if (city == null){
            city = new MutableLiveData<>();
        }
        return city;
    }

    public void setCity(MutableLiveData<String> city) {
        this.city = city;
    }

    public MutableLiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // TODO: 2018/5/4 获取 Users 数据
    }
}
