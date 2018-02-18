package com.bot.androidbasic;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyService extends Service {

    public MyService() {
    }


    private final IBinder mBinder = new MyBinder();

    //Binder
    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        addResultValues();
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        addResultValues();
        return mBinder;
    }

    private List<String> resultList = new ArrayList<>();

    public List<String> getWordList() {
        return resultList;
    }

    public List<String> addSome(String data) {
        resultList.add(data);
        return resultList;
    }

    private void addResultValues() {
        List<String> input = Arrays.asList("Linux", "Android", "iPhone", "Windows7");
        resultList.addAll(input);
    }
}
