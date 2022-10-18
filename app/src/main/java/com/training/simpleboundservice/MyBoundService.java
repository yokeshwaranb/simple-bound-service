package com.training.simpleboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyBoundService extends Service {
    public MyBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}